package com.supergenius.___.deploy;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.ExecUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.supergenius.global.conf.SysConf;

/**
 * @author GreatHost
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class StaticDeployTest {

	private static final String JCBUILD = "/jcbuild";
	private static String webpath;
	private static String jsCompressJar = StrUtil.trim(ClassLoader.getSystemResource("compiler.jar").getFile(), "/");
	private static String cssCompressJar = StrUtil.trim(ClassLoader.getSystemResource("yuicompressor-2.4.7.jar").getFile(), "/");

	@Before
	public void setUp() throws Exception {
		if (webpath == null) {// 第一次执行
			webpath = SysConf.WebAppDir;
			File dirto = new File(webpath + JCBUILD);
			if (dirto.exists()) {
				FileUtils.deleteDirectory(dirto);
			}
			System.out.println("begin to compress javascript & css ...");
		}
	}

	@Test
	public void JSDeploy() throws IOException, InterruptedException {
		File dirfrom = new File(webpath + "/js");
		File dirto = new File(webpath + "/jcbuild/js");
		dirto.mkdirs();
		if (dirfrom.exists() && dirto.exists()) {
			FileUtils.copyDirectory(dirfrom, dirto);
		}

		deleteNotMinJs(new File(dirto.getPath() + "/jquery"));
		deleteNotMinJs(new File(dirto.getPath() + "/html5"));
		deleteNotMinJs(new File(dirto.getPath() + "/ie"));
		deleteNotMinJs(new File(dirto.getPath() + "/libs3"));

		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				String p = f.getPath();
				return (p.endsWith(".js") || p.endsWith("libs") || p.endsWith("pages"));// 匹配需要压缩的文件夹
			}
		};

		File[] files = dirto.listFiles(filter);
		for (File f : files) {
			if (f.isDirectory()) {
				for (File f2 : f.listFiles(filter)) {
					compressJs(f2.getPath());
				}
			} else {
				compressJs(f.getPath());
			}
		}
	}

	private void deleteNotMinJs(File dir) {
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				String p = f.getPath();
				return p.indexOf("min") == -1;
			}
		};
		List<String> paths = FileUtil.getFiles(dir, filter);
		for (String todel : paths) {
			FileUtils.deleteQuietly(new File(todel));
		}
	}

	/**
	 * @param path
	 * @throws ExecuteException
	 * @throws IOException
	 */
	protected void compressJs(String path) throws ExecuteException, IOException {
		String args = " --js=" + path.replace('\\', '/').replaceAll(JCBUILD, "") + " --js_output_file=" + path;
		int exitValue = ExecUtil.execjar(jsCompressJar, args);
		if (exitValue != 0) {
			System.err.println("error compressing:" + path);
		}
	}

	@Test
	public void CssDeploy() throws IOException {
		File dirfrom = new File(webpath + "/css");
		File dirto = new File(webpath + "/jcbuild/css");
		dirto.mkdirs();
		if (dirfrom.exists() && dirto.exists()) {
			FileUtils.copyDirectory(dirfrom, dirto);
		}
		compressCss(dirto);
	}

	/**
	 * @param dirto
	 * @throws IOException
	 * @throws ExecuteException
	 */
	private void compressCss(File dirto) throws ExecuteException, IOException {
		File[] files = dirto.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				compressCss(f);
			} else if (f.getPath().endsWith(".css")) {
				String path = f.getPath();
				String args = " -o " + path + " " + path.replace('\\', '/').replaceAll(JCBUILD, "");
				int exitValue = ExecUtil.execjar(cssCompressJar, args);
				if (exitValue != 0) {
					System.err.println("error compressing:" + path);
				}
			}
		}
	}

	@Test
	public void FckDeploy() throws IOException {
		File dirfrom = new File(webpath + "/ckeditor");
		File dirto = new File(webpath + "/jcbuild/ckeditor");
		dirto.mkdirs();
		if (dirfrom.exists() && dirto.exists()) {
			FileUtils.copyDirectory(dirfrom, dirto);
		}
	}
	
	@Test
	public void fileDeploy() throws IOException {
		File dirfrom = new File(webpath + "/files");
		File dirto = new File(webpath + "/jcbuild/files");
		dirto.mkdirs();
		if (dirfrom.exists() && dirto.exists()) {
			FileUtils.copyDirectory(dirfrom, dirto);
		}
	}

	@Test
	public void ImgDeploy() throws IOException {
		File dirfrom = new File(webpath + "/imgs");
		File dirto = new File(webpath + "/jcbuild/imgs");
		dirto.mkdirs();
		if (dirfrom.exists() && dirto.exists()) {
			FileUtils.copyDirectory(dirfrom, dirto);
		}
	}
}
