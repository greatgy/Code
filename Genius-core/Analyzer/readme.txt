


ʹ��˵����
	1.��solrconfig.xml������<lib dir="${solr.install.dir:../../../..}/mylib" regex=".*\.jar" />
	2.��schema.xml������
	<fieldType name="text_ik" class="solr.TextField">
	  <analyzer type="index" class="com.genius.core.analyzer.lucene.IKAnalyzer" useSmart="true"/>
	  <analyzer type="query" class="com.genius.core.analyzer.lucene.IKAnalyzer" useSmart="true"/>
    </fieldType>
    3.������Ŀ���ɵ�jar����D:\solr5.1.0\mylib��
    