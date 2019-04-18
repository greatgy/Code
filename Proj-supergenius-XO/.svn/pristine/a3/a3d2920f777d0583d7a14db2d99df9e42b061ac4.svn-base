package com.supergenius.xo.___.init;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.moral.moral.dao.StudentDao;
import com.supergenius.xo.moral.entity.Certificate;
import com.supergenius.xo.moral.entity.Student;
import com.supergenius.xo.moral.enums.ELevel;

/**
 * 学员测试数据
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitStudentDataTest {

	@Autowired
	StudentDao dao;

	@Ignore
	@Test
	public void InsertInitData() {
		for (int i = 0; i < 3; i++) {
			Student student = new Student();
			student.setUseruid("ceruseruid" + i);
			student.setSn("cersn" + i);
			student.setScore(Double.valueOf(i));
			student.setLevel(ELevel.one);
			Certificate certificate = new Certificate();
			certificate.setSn("certificatesn" + i);
			certificate.setCreatetime(DateTime.now());
			student.setCertificate(certificate);
			dao.insert(student);
		}
		for (int i = 0; i < 3; i++) {
			Student student = new Student();
			student.setUseruid("useruid" + i);
			student.setSn("sn" + i);
			student.setScore(Double.valueOf(i));
			dao.insert(student);
		}
	}
}
