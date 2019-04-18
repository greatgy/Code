


使用说明：
	1.在solrconfig.xml中增加<lib dir="${solr.install.dir:../../../..}/mylib" regex=".*\.jar" />
	2.在schema.xml中增加
	<fieldType name="text_ik" class="solr.TextField">
	  <analyzer type="index" class="com.genius.core.analyzer.lucene.IKAnalyzer" useSmart="true"/>
	  <analyzer type="query" class="com.genius.core.analyzer.lucene.IKAnalyzer" useSmart="true"/>
    </fieldType>
    3.将本项目生成的jar包放D:\solr5.1.0\mylib中
    