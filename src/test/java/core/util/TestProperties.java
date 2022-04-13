package core.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

public class TestProperties {

	private final Properties props = new Properties();

	public TestProperties() {
		this.props.putAll(PropertyUtils.getProps());
	}

	public int getIntegerProperty(final String key, final int defaultValue) {
		int integerValue = defaultValue;
		final String value = this.props.getProperty(key);
		if (value != null) {
			integerValue = Integer.parseInt(value);
		}
		return integerValue;
	}

	private BigDecimal getBigDecimalValue(double intValue, int scale) {
		return BigDecimal.valueOf(intValue).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * This method returns comma separated values as array of string
	 * 
	 * @param key
	 */
	public String[] getPropertyArray(final String key) {
		return getProperty(key).split(",");
	}

	public String getProperty(final String key) {
		return this.props.getProperty(key);
	}

	public String getProperty(final String key, final String defaultValue) {
		return this.props.getProperty(key, defaultValue);
	}

	public void loadProperties(final String path) {
		try {
			final InputStream s = ClassLoader.getSystemResourceAsStream(path);
			this.props.load(s);
		} catch (final Exception ex) {
			throw new UnableToLoadPropertiesException("Unable to load '" + path + "' properties file", ex);
		}
	}
}
