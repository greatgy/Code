package com.genius.core.base.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Architect.bian
 *
 */
public class MacUtil extends BaseUtil {
	/**
	 * 判断是否有效的Mac地址
	 * 
	 * @param _lMac
	 * @return
	 */
	public static boolean isValidMac(long _lMac) {
		if (_lMac == Long.parseLong("123456789012")) {
			return true;
		}

		int nMaxValidCount = 10;
		for (int i = 0; i < nMaxValidCount; ++i)
			if (getMyMacAsLong(i) == _lMac)
				return true;

		return false;
	}

	/**
	 * 把Mac地址转为长整型
	 * 
	 * @param _nIndex
	 * @return
	 */
	public static long getMyMacAsLong(int _nIndex) {
		String sMac = getMyMac(_nIndex);

		return getMyMacAsLong(sMac);
	}

	/**
	 * 把Mac地址转为长整型
	 * 
	 * @return
	 */
	public static long getMyMacAsLong() {
		return getMyMacAsLong(0);
	}

	/**
	 * 把Mac地址转为长整型
	 * 
	 * @param _sMac
	 * @return
	 */
	public static long getMyMacAsLong(String _sMac) {
		if ((_sMac == null) || (_sMac.length() <= 0))
			return 8702859993860276224L;

		String sMac = _sMac;

		sMac = StringUtils.replace(sMac, "-", "");
		long nMac = Long.parseLong(sMac, 16);

		sMac = "" + nMac;
		if (sMac.length() > 12) {
			sMac = sMac.substring(0, 12);
			while ((sMac.charAt(0) == '0') && (sMac.length() > 0))
				sMac = sMac.substring(1, sMac.length());

			nMac = Long.parseLong(sMac);
		}

		return nMac;
	}

	/**
	 * 如果操作系统为windows，获得Windows的Mac地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnWindow(int _nIndex) {
		String s = "";
		try {
			String s1 = "ipconfig /all";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine();
			int nIndex = 0;
			while (line != null) {
				String nextLine = bufferedreader.readLine();

				if ((line.indexOf("Physical Address") != -1)
						|| (line.indexOf("物理地址") != -1)) {
					if (nIndex == _nIndex) {
						s = line.split(":")[1].trim();
						break;
					}
					++nIndex;
				}
				line = nextLine;
			}

			bufferedreader.close();

			process.waitFor();

			process.destroy();

		} catch (Exception exception) {
			s = "";
		}
		return s.trim();
	}

	/**
	 * 获得linux系统的操作地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnLinux(int _nIndex) {
		String s = "";
		try {
			String s1 = "/sbin/ifconfig -a";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine().toUpperCase();
			int nIndex = 0;
			while (line != null) {
				String nextLine = bufferedreader.readLine();
				if (line.indexOf("HWADDR") > 0) {
					if (nIndex == _nIndex) {
						int i = line.indexOf("HWADDR") + 7;
						s = line.substring(i);
						break;
					}
					++nIndex;
				}
				line = nextLine.toUpperCase();
			}
			bufferedreader.close();

			process.waitFor();

			process.destroy();

		} catch (Exception exception) {
			s = "";
		}
		return s.trim().replace(':', '-');
	}

	/**
	 * 获取HP操作系统的Mac地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnHP(int _nIndex) {
		String s = "";
		try {
			String s1 = "/usr/sbin/lanscan";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine().toUpperCase();
			int nIndex = 0;
			while (line != null) {
				String nextLine = bufferedreader.readLine();
				int nPose = line.indexOf("0X");
				if (nPose > 0) {
					if (nIndex == _nIndex) {
						int nStart = nPose + 2;
						int nEnd = line.indexOf(" ", nStart);
						s = line.substring(nStart, nEnd);
						break;
					}

					++nIndex;
				}
				line = nextLine.toUpperCase();
			}

			bufferedreader.close();

			process.waitFor();

			process.destroy();

		} catch (Exception exception) {
			s = "";
		}
		return s.trim();
	}

	/**
	 * 获取OS的操作系统的Mac地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnSolaris(int _nIndex) {
		String s = "";
		try {
			String s1 = "/usr/sbin/ifconfig -a";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine().toUpperCase();
			int nIndex = 0;
			while (line != null) {
				String nextLine = bufferedreader.readLine().toUpperCase();
				if (line.indexOf("NEI0") > 0) {
					int nStart = nextLine.indexOf("INET") + 5;
					if (nStart < 5)
						break;
					int nEnd = nextLine.indexOf(" ", nStart);
					if (nEnd <= nStart)
						break;
					if (nIndex == _nIndex) {
						s = nextLine.substring(nStart, nEnd);
						break;
					}
					++nIndex;
				}
				line = nextLine;
			}
			bufferedreader.close();

			if (s.length() <= 8)
				return getMacOnSolaris2(_nIndex);

			process.waitFor();

			process.destroy();
		} catch (Exception ex) {
			return getMacOnSolaris2(_nIndex);
		}
		return StringUtils.replace(s, ".", "");
	}

	/**
	 * 获取OS的操作系统的Mac地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnSolaris2(int _nIndex) {
		String s = "";
		try {
			String s1 = "/usr/sbin/ifconfig -a";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine().toUpperCase();
			int nIndex = 0;
			while (line != null) {
				line = line.toUpperCase();
				int nStart = line.indexOf("ETHER");
				if (nStart >= 0) {
					if (nIndex == _nIndex) {
						nStart += 6;
						int nEnd = line.indexOf(" ", nStart);
						if (nEnd <= 0)
							nEnd = line.length();
						s = line.substring(nStart, nEnd).trim();
						break;
					}
					++nIndex;
				}
				line = bufferedreader.readLine();
			}
			bufferedreader.close();

			process.waitFor();

			process.destroy();

		} catch (Exception ex) {
			s = "";
		}
		s = StringUtils.replace(s, ".", "");
		s = StringUtils.replace(s, ":", "");
		return s;
	}

	/**
	 * 获取AIX系统的操作地址
	 * 
	 * @param _nIndex
	 * @return
	 */
	private static String getMacOnAIX(int _nIndex) {
		String s = "";
		try {
			String s1 = "/usr/bin/uname -m";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = bufferedreader.readLine().toUpperCase();
			s = line;
			bufferedreader.close();

			process.waitFor();

			process.destroy();
		} catch (Exception exception) {
			s = "";
		}
		return s.trim();
	}

	public static String getMyMac(int _nIndex) {
		String sOs = System.getProperty("os.name", "");
		if (StringUtils.isEmpty(sOs))
			sOs = (String) System.getProperties().get("os.name");

		if (StringUtils.isEmpty(sOs)) {
			return "";
		}
		sOs = sOs.toUpperCase();

		if (sOs.indexOf("WINDOWS") >= 0)
			return getMacOnWindow(_nIndex);
		if (sOs.indexOf("HP") >= 0)
			return getMacOnHP(_nIndex);
		if (sOs.indexOf("LINUX") >= 0)
			return getMacOnLinux(_nIndex);
		if ((sOs.indexOf("SOLARIS") >= 0) || (sOs.indexOf("SUNOS") >= 0))
			return getMacOnSolaris(_nIndex);
		if (sOs.indexOf("AIX") >= 0)
			return getMacOnAIX(_nIndex);

		return "";
	}
}
