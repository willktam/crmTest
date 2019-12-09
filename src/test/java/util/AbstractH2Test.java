package util;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.jboss.weld.environment.se.Weld;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.skyve.EXT;
import org.skyve.impl.cdi.SkyveCDIProducer;
import org.skyve.impl.content.AbstractContentManager;
import org.skyve.impl.content.NoOpContentManager;
import org.skyve.impl.metadata.repository.AbstractRepository;
import org.skyve.impl.metadata.repository.LocalDesignRepository;
import org.skyve.impl.metadata.user.SuperUser;
import org.skyve.impl.persistence.AbstractPersistence;
import org.skyve.impl.persistence.hibernate.HibernateContentPersistence;
import org.skyve.impl.util.UtilImpl;
import org.skyve.persistence.DataStore;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.admin.User.UserExtension;
import modules.admin.domain.User;

public abstract class AbstractH2Test {
	protected static final String USER = "TestUser";
	protected static final String PASSWORD = "TestPassword0!";
	protected static final String CUSTOMER = "skyve";

	private static final String DB_DIALECT = "org.skyve.impl.persistence.hibernate.dialect.H2SpatialDialect";
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:mem:test";
	private static final String DB_UNAME = "user";
	private static final String DB_PWD = "password";
	private static final String CONTENT_DIRECTORY = "./target/test/content/";

	// Add common mocks here
	// @Mock
	// protected WebContext webContext;

	private static Weld weld;

	public AbstractH2Test() {
		// support injected classes in unit tests
		BeanProvider.injectFields(this);
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		weld = new Weld();
		weld.addPackage(true, SkyveCDIProducer.class);

		weld.initialize();
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		if (weld != null) {
			weld.shutdown();
		}
	}

	@Before
	public void beforeBase() throws Exception {
		AbstractPersistence.IMPLEMENTATION_CLASS = HibernateContentPersistence.class;
		AbstractContentManager.IMPLEMENTATION_CLASS = NoOpContentManager.class;
		UtilImpl.DATA_STORE = new DataStore(DB_DRIVER, DB_URL, DB_UNAME, DB_PWD, DB_DIALECT);
		UtilImpl.DATA_STORES.put("test", UtilImpl.DATA_STORE);
		UtilImpl.DDL_SYNC = true;
		UtilImpl.SQL_TRACE = false;
		UtilImpl.QUERY_TRACE = false;
		UtilImpl.CONTENT_DIRECTORY = CONTENT_DIRECTORY;

		AbstractRepository.set(new LocalDesignRepository());

		final SuperUser user = new SuperUser();
		user.setCustomerName(CUSTOMER);
		user.setName(USER);
		user.setId(USER);

		final AbstractPersistence persistence = AbstractPersistence.get();
		persistence.setUser(user);
		persistence.begin();

		// create admin user
		User adminUser = createAdminUser(user);
		persistence.save(adminUser);
	}

	@After
	public void afterBase() {
		final AbstractPersistence persistence = AbstractPersistence.get();
		persistence.rollback();
		persistence.evictAllCached();
	}

	/**
	 * Create a new {@link User} which corresponds to the metadata superuser
	 * running as the current persistence user so that requests to currentAdminUser resolve.
	 */
	private static UserExtension createAdminUser(SuperUser superUser) {
		UserExtension adminUser = new DataBuilder().fixture(FixtureType.crud).build(User.MODULE_NAME, User.DOCUMENT_NAME);
		adminUser.setUserName(superUser.getName());
		adminUser.setPassword(EXT.hashPassword(PASSWORD));
		adminUser.setPasswordHistory(null);
		superUser.setContactId(adminUser.getContact().getBizId());
		adminUser.setBizId(superUser.getId());
		return adminUser;
	}
}
