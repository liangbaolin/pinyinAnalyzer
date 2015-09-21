# pinyinAnalyzer
=================
solr的中文拼音分词过滤器，支持全拼，简拼和简拼和全拼同时输出，同时提供了一个基于NGram算法的类似EdgeNGramTokenFilter的过滤器，但实现了双向过滤。

在Solr 4.3.0版本ji及及以上版本中测试通过。

#Example/示例

>>  <fieldtype name="text" class="solr.TextField">
>>   <analyzer type="index">
>      <tokenizer class="org.lionsoul.jcseg.analyzer.JcsegTokenizerFactory" mode="complex"/>
>        <filter class="org.liangbl.solr.analysis.PinyinTransformTokenFilterFactory" isOutChinese="true" 
>          outputFormat="both" minTermLength="1"/>
>        <filter class="org.liangbl.solr.analysis.PinyinNGramTokenFilterFactory" minGramSize="1" maxGramSize="20"/>
>        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
>        <filter class="solr.LowerCaseFilterFactory" />
>        <filter class="solr.RemoveDuplicatesTokenFilterFactory"/>
>      </analyzer>
>      <analyzer type="query">
>        <tokenizer class="org.lionsoul.jcseg.analyzer.JcsegTokenizerFactory" mode="complex"/>
>        <filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt"
>              ignoreCase="true" expand="true" />
>        <filter class="solr.StopFilterFactory" ignoreCase="true"
>              words="stopwords.txt" />
>        <filter class="solr.LowerCaseFilterFactory" />
>      </analyzer>
>    </fieldtype>


#过滤器类
*org.liangbl.solr.analysis.PinyinTransformTokenFilterFactory*
*org.liangbl.solr.analysis.PinyinNGramTokenFilterFactory*

#Configuration/配置项
##isOutChinese
If original chinese term would keep in output or not.Optional values:*true*(default)/*false*.  
是否保留原输入中文词元。可选值：*true*(默认)/*false*

##outputFormat
If output pinyin would be in full format or in short format or both.The short format is formed by every first character of pinyin of every chinese character.Optional values:*both*(default)/*full*short*.  
输出完整拼音格式还是输出简拼或者两者。简拼输出是由原中文词元的各单字的拼音结果的首字母组成的。可选值：*both*(default)/*full*short*

##minTermLength
Only output pinyin term for chinese term which character lenght is greater than or equals *minTermLenght*.The default value is 2.
仅输出字数大于或等于*minTermLenght*的中文词元的拼音结果。默认值为2。

##minGramSize
the smallest n-gram to generate
最小拼音切分长度。

##minGramSize
the largest n-gram to generate
最大拼音切分长度。

关于依赖＆构建＆部署
-----------------

#*部署的时候别忘了把pinyin4j的jar包也拷贝到solr项目的lib路径下！*
