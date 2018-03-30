package com.bola.nwcl.common.util.jsonhelper;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 处理返回json值为null时转化为empty
 *                       
 * @Filename JsonFilter.java
 *
 */
public final class JsonFilter {
	public static final String toSpecialJSONStringWithDateFormat(Object object, String dateFormat, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();

		try {
			JSONSerializer serializer = new JSONSerializer(out);
			for (SerializerFeature feature : features) {
				serializer.config(feature, true);
			}

			serializer.config(SerializerFeature.WriteDateUseDateFormat, true);

			if (dateFormat != null) {
				serializer.setDateFormat(dateFormat);
			}

			serializer.getValueFilters().add(new ValueFilter() {
				public Object process(Object obj, String s, Object value) {
					if (null != value) {
						return value;
					} else {
						return "";
					}
				}
			});

			serializer.write(object);

			return out.toString();
		} finally {
			out.close();
		}
	}
}
