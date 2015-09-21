package org.liangbl.solr.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.liangbl.solr.analysis.PinyinTransformTokenFilter.OutputFormat;



/**
 * 拼音转换分词过滤器工厂类
 * @author liufl / 2014年7月2日
 */
public class PinyinTransformTokenFilterFactory extends TokenFilterFactory  {

	private boolean isOutChinese = PinyinTransformTokenFilter.DEFAULT_IS_OUT_CHINESE; // 是否输出原中文开关
	private int minTermLength = PinyinTransformTokenFilter.DEFAULT_MINTERMLENGTH; // 中文词组长度过滤，默认超过2位长度的中文才转换拼音
	private OutputFormat outputFormat = PinyinTransformTokenFilter.DEFAULT_OUTPUTFORMAT; // 拼音简拼、全拼和两者的开关，默认简拼和全拼全部索引，1为简拼2为全拼，0为两者

	/**
	 * 构造器
	 * @param args
	 */
	public PinyinTransformTokenFilterFactory(Map<String, String> args) {
		super(args);
		this.isOutChinese = getBoolean(args, "isOutChinese", PinyinTransformTokenFilter.DEFAULT_IS_OUT_CHINESE);
		this.outputFormat = OutputFormat.getOutFormat(get(args, "outputFormat", PinyinTransformTokenFilter.DEFAULT_OUTPUTFORMAT.getLabel()));
		this.minTermLength = getInt(args, "minTermLength", PinyinTransformTokenFilter.DEFAULT_MINTERMLENGTH);
		if (!args.isEmpty())
			throw new IllegalArgumentException("Unknown parameters: " + args);
	}

	public TokenFilter create(TokenStream input) {
		return new PinyinTransformTokenFilter(input, this.outputFormat,
				this.minTermLength, this.isOutChinese);
	}
}
