package modules.admin.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.admin.Startup.StartupExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractTransientBean;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Startup Configuration
 * <br/>
 * This document provides a visual way to edit the JSON configuration
		and is shown to the administrator by default on first login.
 * 
 * @depend - - - MapType
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class Startup extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Startup";

	/** @hidden */
	public static final String dontShowAgainPropertyName = "dontShowAgain";
	/** @hidden */
	public static final String environmentIdentifierPropertyName = "environmentIdentifier";
	/** @hidden */
	public static final String environmentSupportEmailPropertyName = "environmentSupportEmail";
	/** @hidden */
	public static final String mapTypePropertyName = "mapType";
	/** @hidden */
	public static final String mapZoomPropertyName = "mapZoom";
	/** @hidden */
	public static final String mapLayerPropertyName = "mapLayer";
	/** @hidden */
	public static final String mapCentrePropertyName = "mapCentre";
	/** @hidden */
	public static final String mailServerUrlPropertyName = "mailServerUrl";
	/** @hidden */
	public static final String mailPortPropertyName = "mailPort";
	/** @hidden */
	public static final String mailUsernamePropertyName = "mailUsername";
	/** @hidden */
	public static final String mailPasswordPropertyName = "mailPassword";
	/** @hidden */
	public static final String mailSenderPropertyName = "mailSender";
	/** @hidden */
	public static final String mailBogusSendPropertyName = "mailBogusSend";
	/** @hidden */
	public static final String mailTestRecipientPropertyName = "mailTestRecipient";
	/** @hidden */
	public static final String apiGoogleMapsKeyPropertyName = "apiGoogleMapsKey";
	/** @hidden */
	public static final String apiGoogleRecaptchaKeyPropertyName = "apiGoogleRecaptchaKey";
	/** @hidden */
	public static final String apiTwilioSIDPropertyName = "apiTwilioSID";
	/** @hidden */
	public static final String apiTwilioAuthTokenPropertyName = "apiTwilioAuthToken";
	/** @hidden */
	public static final String apiTwilioDefaultSendNumberPropertyName = "apiTwilioDefaultSendNumber";

	/**
	 * Type
	 * <br/>
	 * Which map technology would you like to use for this Skyve application? Note: Google Maps requires an API key.
	 **/
	@XmlEnum
	public static enum MapType implements Enumeration {
		gmap("gmap", "Google Maps"),
		leaflet("leaflet", "Open Street Map");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private MapType(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static MapType fromCode(String code) {
			MapType result = null;

			for (MapType value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static MapType fromDescription(String description) {
			MapType result = null;

			for (MapType value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				MapType[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (MapType value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Don't show this again
	 * <br/>
	 * Allow the user to bypass the setup screen and set the showSetup value to false.
	 **/
	private Boolean dontShowAgain = new Boolean(false);
	/**
	 * Identifier
	 * <br/>
	 * The description of this environment, e.g. Test, UAT. Leave this blank for production.
	 **/
	private String environmentIdentifier;
	/**
	 * Support Email Address
	 * <br/>
	 * Email address for system support
	 **/
	private String environmentSupportEmail;
	/**
	 * Type
	 * <br/>
	 * Which map technology would you like to use for this Skyve application? Note: Google Maps requires an API key.
	 **/
	private MapType mapType = MapType.leaflet;
	/**
	 * Zoom
	 * <br/>
	 * What should the default zoom level be when opening a new map (value between 1-19)?
	 **/
	private Integer mapZoom = new Integer(1);
	/**
	 * Layers
	 * <br/>
	 * Google Map or Leaflet layer to show the map backdrop
	 **/
	private String mapLayer;
	/**
	 * Centre
	 * <br/>
	 * Where to centre a new map when it opens
	 **/
	private Geometry mapCentre;
	/**
	 * Server URL
	 * <br/>
	 * URL or IP address of the SMTP server to use
	 **/
	private String mailServerUrl;
	/**
	 * Server Port
	 * <br/>
	 * Which port should be used to access the mail server? This is usually 25, 465 or 587 depending if it is secure or insecure.
	 **/
	private Integer mailPort = new Integer(25);
	/**
	 * Username
	 * <br/>
	 * Mail server username
	 **/
	private String mailUsername;
	/**
	 * Password
	 * <br/>
	 * Mail server password
	 **/
	private String mailPassword;
	/**
	 * Default Sender
	 * <br/>
	 * Default send from email address
	 **/
	private String mailSender;
	/**
	 * Test Mode
	 * <br/>
	 * If true, email is disabled and just logged, it will never be sent
	 **/
	private Boolean mailBogusSend = new Boolean(false);
	/**
	 * Test Mail Recipient
	 * <br/>
	 * All emails will only be sent to this email address if specified
	 **/
	private String mailTestRecipient;
	/**
	 * Google Maps Key
	 * <br/>
	 * If using Google Maps for your map type, specify your map key here
	 **/
	private String apiGoogleMapsKey;
	/**
	 * Google Recaptcha Site Key
	 * <br/>
	 * To enable the forgot password function, this application must be registered 
					for Google Recaptcha and the site key must be specified here.
	 **/
	private String apiGoogleRecaptchaKey;
	/**
	 * Account SID
	 **/
	private String apiTwilioSID;
	/**
	 * Account SID
	 **/
	private String apiTwilioAuthToken;
	/**
	 * Default Send Number
	 **/
	private String apiTwilioDefaultSendNumber;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Startup.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Startup.DOCUMENT_NAME;
	}

	public static StartupExtension newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		return toString();

	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Startup) && 
					this.getBizId().equals(((Startup) o).getBizId()));
	}

	/**
	 * {@link #dontShowAgain} accessor.
	 * @return	The value.
	 **/
	public Boolean getDontShowAgain() {
		return dontShowAgain;
	}

	/**
	 * {@link #dontShowAgain} mutator.
	 * @param dontShowAgain	The new value.
	 **/
	@XmlElement
	public void setDontShowAgain(Boolean dontShowAgain) {
		preset(dontShowAgainPropertyName, dontShowAgain);
		this.dontShowAgain = dontShowAgain;
	}

	/**
	 * {@link #environmentIdentifier} accessor.
	 * @return	The value.
	 **/
	public String getEnvironmentIdentifier() {
		return environmentIdentifier;
	}

	/**
	 * {@link #environmentIdentifier} mutator.
	 * @param environmentIdentifier	The new value.
	 **/
	@XmlElement
	public void setEnvironmentIdentifier(String environmentIdentifier) {
		preset(environmentIdentifierPropertyName, environmentIdentifier);
		this.environmentIdentifier = environmentIdentifier;
	}

	/**
	 * {@link #environmentSupportEmail} accessor.
	 * @return	The value.
	 **/
	public String getEnvironmentSupportEmail() {
		return environmentSupportEmail;
	}

	/**
	 * {@link #environmentSupportEmail} mutator.
	 * @param environmentSupportEmail	The new value.
	 **/
	@XmlElement
	public void setEnvironmentSupportEmail(String environmentSupportEmail) {
		preset(environmentSupportEmailPropertyName, environmentSupportEmail);
		this.environmentSupportEmail = environmentSupportEmail;
	}

	/**
	 * {@link #mapType} accessor.
	 * @return	The value.
	 **/
	public MapType getMapType() {
		return mapType;
	}

	/**
	 * {@link #mapType} mutator.
	 * @param mapType	The new value.
	 **/
	@XmlElement
	public void setMapType(MapType mapType) {
		preset(mapTypePropertyName, mapType);
		this.mapType = mapType;
	}

	/**
	 * {@link #mapZoom} accessor.
	 * @return	The value.
	 **/
	public Integer getMapZoom() {
		return mapZoom;
	}

	/**
	 * {@link #mapZoom} mutator.
	 * @param mapZoom	The new value.
	 **/
	@XmlElement
	public void setMapZoom(Integer mapZoom) {
		preset(mapZoomPropertyName, mapZoom);
		this.mapZoom = mapZoom;
	}

	/**
	 * {@link #mapLayer} accessor.
	 * @return	The value.
	 **/
	public String getMapLayer() {
		return mapLayer;
	}

	/**
	 * {@link #mapLayer} mutator.
	 * @param mapLayer	The new value.
	 **/
	@XmlElement
	public void setMapLayer(String mapLayer) {
		preset(mapLayerPropertyName, mapLayer);
		this.mapLayer = mapLayer;
	}

	/**
	 * {@link #mapCentre} accessor.
	 * @return	The value.
	 **/
	public Geometry getMapCentre() {
		return mapCentre;
	}

	/**
	 * {@link #mapCentre} mutator.
	 * @param mapCentre	The new value.
	 **/
	@XmlJavaTypeAdapter(GeometryMapper.class)
	@XmlElement
	public void setMapCentre(Geometry mapCentre) {
		preset(mapCentrePropertyName, mapCentre);
		this.mapCentre = mapCentre;
	}

	/**
	 * {@link #mailServerUrl} accessor.
	 * @return	The value.
	 **/
	public String getMailServerUrl() {
		return mailServerUrl;
	}

	/**
	 * {@link #mailServerUrl} mutator.
	 * @param mailServerUrl	The new value.
	 **/
	@XmlElement
	public void setMailServerUrl(String mailServerUrl) {
		preset(mailServerUrlPropertyName, mailServerUrl);
		this.mailServerUrl = mailServerUrl;
	}

	/**
	 * {@link #mailPort} accessor.
	 * @return	The value.
	 **/
	public Integer getMailPort() {
		return mailPort;
	}

	/**
	 * {@link #mailPort} mutator.
	 * @param mailPort	The new value.
	 **/
	@XmlElement
	public void setMailPort(Integer mailPort) {
		preset(mailPortPropertyName, mailPort);
		this.mailPort = mailPort;
	}

	/**
	 * {@link #mailUsername} accessor.
	 * @return	The value.
	 **/
	public String getMailUsername() {
		return mailUsername;
	}

	/**
	 * {@link #mailUsername} mutator.
	 * @param mailUsername	The new value.
	 **/
	@XmlElement
	public void setMailUsername(String mailUsername) {
		preset(mailUsernamePropertyName, mailUsername);
		this.mailUsername = mailUsername;
	}

	/**
	 * {@link #mailPassword} accessor.
	 * @return	The value.
	 **/
	public String getMailPassword() {
		return mailPassword;
	}

	/**
	 * {@link #mailPassword} mutator.
	 * @param mailPassword	The new value.
	 **/
	@XmlElement
	public void setMailPassword(String mailPassword) {
		preset(mailPasswordPropertyName, mailPassword);
		this.mailPassword = mailPassword;
	}

	/**
	 * {@link #mailSender} accessor.
	 * @return	The value.
	 **/
	public String getMailSender() {
		return mailSender;
	}

	/**
	 * {@link #mailSender} mutator.
	 * @param mailSender	The new value.
	 **/
	@XmlElement
	public void setMailSender(String mailSender) {
		preset(mailSenderPropertyName, mailSender);
		this.mailSender = mailSender;
	}

	/**
	 * {@link #mailBogusSend} accessor.
	 * @return	The value.
	 **/
	public Boolean getMailBogusSend() {
		return mailBogusSend;
	}

	/**
	 * {@link #mailBogusSend} mutator.
	 * @param mailBogusSend	The new value.
	 **/
	@XmlElement
	public void setMailBogusSend(Boolean mailBogusSend) {
		preset(mailBogusSendPropertyName, mailBogusSend);
		this.mailBogusSend = mailBogusSend;
	}

	/**
	 * {@link #mailTestRecipient} accessor.
	 * @return	The value.
	 **/
	public String getMailTestRecipient() {
		return mailTestRecipient;
	}

	/**
	 * {@link #mailTestRecipient} mutator.
	 * @param mailTestRecipient	The new value.
	 **/
	@XmlElement
	public void setMailTestRecipient(String mailTestRecipient) {
		preset(mailTestRecipientPropertyName, mailTestRecipient);
		this.mailTestRecipient = mailTestRecipient;
	}

	/**
	 * {@link #apiGoogleMapsKey} accessor.
	 * @return	The value.
	 **/
	public String getApiGoogleMapsKey() {
		return apiGoogleMapsKey;
	}

	/**
	 * {@link #apiGoogleMapsKey} mutator.
	 * @param apiGoogleMapsKey	The new value.
	 **/
	@XmlElement
	public void setApiGoogleMapsKey(String apiGoogleMapsKey) {
		preset(apiGoogleMapsKeyPropertyName, apiGoogleMapsKey);
		this.apiGoogleMapsKey = apiGoogleMapsKey;
	}

	/**
	 * {@link #apiGoogleRecaptchaKey} accessor.
	 * @return	The value.
	 **/
	public String getApiGoogleRecaptchaKey() {
		return apiGoogleRecaptchaKey;
	}

	/**
	 * {@link #apiGoogleRecaptchaKey} mutator.
	 * @param apiGoogleRecaptchaKey	The new value.
	 **/
	@XmlElement
	public void setApiGoogleRecaptchaKey(String apiGoogleRecaptchaKey) {
		preset(apiGoogleRecaptchaKeyPropertyName, apiGoogleRecaptchaKey);
		this.apiGoogleRecaptchaKey = apiGoogleRecaptchaKey;
	}

	/**
	 * {@link #apiTwilioSID} accessor.
	 * @return	The value.
	 **/
	public String getApiTwilioSID() {
		return apiTwilioSID;
	}

	/**
	 * {@link #apiTwilioSID} mutator.
	 * @param apiTwilioSID	The new value.
	 **/
	@XmlElement
	public void setApiTwilioSID(String apiTwilioSID) {
		preset(apiTwilioSIDPropertyName, apiTwilioSID);
		this.apiTwilioSID = apiTwilioSID;
	}

	/**
	 * {@link #apiTwilioAuthToken} accessor.
	 * @return	The value.
	 **/
	public String getApiTwilioAuthToken() {
		return apiTwilioAuthToken;
	}

	/**
	 * {@link #apiTwilioAuthToken} mutator.
	 * @param apiTwilioAuthToken	The new value.
	 **/
	@XmlElement
	public void setApiTwilioAuthToken(String apiTwilioAuthToken) {
		preset(apiTwilioAuthTokenPropertyName, apiTwilioAuthToken);
		this.apiTwilioAuthToken = apiTwilioAuthToken;
	}

	/**
	 * {@link #apiTwilioDefaultSendNumber} accessor.
	 * @return	The value.
	 **/
	public String getApiTwilioDefaultSendNumber() {
		return apiTwilioDefaultSendNumber;
	}

	/**
	 * {@link #apiTwilioDefaultSendNumber} mutator.
	 * @param apiTwilioDefaultSendNumber	The new value.
	 **/
	@XmlElement
	public void setApiTwilioDefaultSendNumber(String apiTwilioDefaultSendNumber) {
		preset(apiTwilioDefaultSendNumberPropertyName, apiTwilioDefaultSendNumber);
		this.apiTwilioDefaultSendNumber = apiTwilioDefaultSendNumber;
	}

	/**
	 * True when the selected map type is Google Maps
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isMapTypeGmap() {
		return (MapType.gmap == getMapType());
	}

	/**
	 * {@link #isMapTypeGmap} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotMapTypeGmap() {
		return (! isMapTypeGmap());
	}
}
