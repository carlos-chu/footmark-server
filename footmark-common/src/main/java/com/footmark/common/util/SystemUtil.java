package com.footmark.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemUtil {

    @SuppressWarnings("rawtypes")
    public static List<Class> getImplementingClassesByInterface(Class interfaceType) {
        List<Class> typeList = new ArrayList<Class>();
        try {
            Class[] clazArr = interfaceType.getClasses();
            for (int i = 0; i < clazArr.length; i++) {
                typeList.add(clazArr[i]);
            }
        } catch (Exception ex) {
        }
        return typeList;
    }

    @SuppressWarnings("rawtypes")
    protected static Boolean isInterfaceImplemented(String className, String interfaceName) {
        try {
            Class classType = Class.forName(className);
            Class interfaceType = Class.forName(interfaceName);
            if (!classType.isLocalClass() || !interfaceType.isInterface()) {
                return false;
            }
            return isInterfaceImplemented(classType, interfaceType);
        } catch (Exception ex) {
            return false;
        }

    }

    @SuppressWarnings("rawtypes")
    protected static Boolean isInterfaceImplemented(Object obj, Class interfaceType) {
        return isInterfaceImplemented(obj.getClass(), interfaceType);
    }

    @SuppressWarnings("rawtypes")
    protected static Boolean isInterfaceImplemented(Class classType, Class interfaceType) {
        Class[] interfaceList = classType.getInterfaces();
        for (int i = 0; i < interfaceList.length; i++) {
            if (interfaceList[i].equals(classType)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] uuidAsByteArray(UUID uuid) {
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }

        return buffer;
    }

    // byte数组转换�?6进制字符�?
    public static String byte2hex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String temp = Integer.toHexString(((int) data[i]) & 0xFF);
            for (int t = temp.length(); t < 2; t++) {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    // 16进制转换为byte数组
    public static byte[] hex2byte(String hexStr) {
        byte[] bts = new byte[hexStr.length() / 2];
        for (int i = 0, j = 0; j < bts.length; j++) {
            bts[j] = (byte) Integer.parseInt(hexStr.substring(i, i + 2), 16);
            i += 2;
        }
        return bts;
    }

    public static String firstLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String firstUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void copyfile(String srFile, String dtFile) {
        try {
            File f1 = new File(srFile);
            if (!f1.exists()) {
                return;
            }
            File f2 = new File(dtFile);
            File dir = new File(f2.getAbsolutePath().replace(f2.getName(), ""));
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    return;
                }
            }
            InputStream in = new FileInputStream(f1);

            // For Append the file.
            // OutputStream out = new FileOutputStream(f2,true);

            // For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    public final static byte[] getBytes(short s, boolean asc) {
        byte[] buf = new byte[2];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        return buf;
    }

    public final static byte[] getBytes(int s, boolean asc) {
        byte[] buf = new byte[4];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        return buf;
    }

    public final static byte[] getBytes(long s, boolean asc) {
        byte[] buf = new byte[8];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        return buf;
    }

    public final static short getShort(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        short r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        return r;
    }

    public final static int getInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    public final static long getLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
        long r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        return r;
    }

    public static void fileRecursing(String root, Vector<String> vecFile) {
        File file = new File(root);
        File[] subFile = file.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            if (subFile[i].isDirectory()) {
                fileRecursing(subFile[i].getAbsolutePath(), vecFile);
            } else {
                vecFile.add(subFile[i].getAbsolutePath());
            }
        }
    }

    // public static void fileRecursing(String root, Vector<String> vecFile,
    // String fileName) {
    // File file = new File(root);
    // File[] subFile = file.listFiles();
    // for (int i = 0; i < subFile.length; i++) {
    // if (subFile[i].isDirectory()) {
    // fileRecursing(subFile[i].getAbsolutePath(), vecFile,
    // new FileNameFilter(fileName.toLowerCase()));
    // if (vecFile.size() > 0) {
    // break;
    // }
    // } else {
    // if (subFile[i].getAbsolutePath().toLowerCase()
    // .endsWith(fileName)) {
    // vecFile.add(subFile[i].getAbsolutePath());
    // break;
    // }
    // }
    // }
    // }

    // protected static void fileRecursing(String root, Vector<String> vecFile,
    // FileNameFilter filter) {
    // File file = new File(root);
    // File[] subFile = file.listFiles(filter);
    // if (subFile != null && subFile.length > 0) {
    // for (int i = 0; i < subFile.length; i++) {
    // vecFile.add(subFile[i].getAbsolutePath());
    // }
    // return;
    // }
    // subFile = file.listFiles();
    // for (int i = 0; i < subFile.length; i++) {
    // if (subFile[i].isDirectory()) {
    // fileRecursing(subFile[i].getAbsolutePath(), vecFile, filter);
    // }
    // }
    // }

    public static void fileRecursing(String root, Map<String, String> vecFile) {
        File file = new File(root);
        File[] subFile = file.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            if (subFile[i].isDirectory()) {
                fileRecursing(subFile[i].getAbsolutePath(), vecFile);
            } else {
                vecFile.put(subFile[i].getName(), subFile[i].getAbsolutePath());
            }
        }
    }

    public static int writeFileLines(String fileName, List<String> contentList) {
        FileWriter writer = null;
        BufferedWriter bw = null;
        try {
            StringBuilder contentBuilder = new StringBuilder();
            for (String ums : contentList) {
                contentBuilder.append(ums + "\n");
            }
            writer = new FileWriter(fileName, true);
            bw = new BufferedWriter(writer);
            bw.write(contentBuilder.toString());
            return contentList.size();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static List<String> readFileLines(String fileName) {
        FileReader reader = null;
        BufferedReader br = null;
        try {
            reader = new FileReader(fileName);
            br = new BufferedReader(reader);
            String s = null;
            List<String> lines = new ArrayList<String>();
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
            return lines;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String readFileAsString(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return readFileAsString(fis);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String readFileAsString(InputStream stream) {
        StringBuffer fileData = new StringBuffer(1000);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(stream, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
            return fileData.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileAsString(File file, int length) {
        StringBuffer fileData = new StringBuffer(1000);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            char[] buf = new char[1024];
            int readChars = 0;
            int numRead = 0;
            while ((readChars < length && (numRead = reader.read(buf)) != -1)) {
                readChars += numRead;
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
            return fileData.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void quickSort(int a[], int left, int right) {
        int i, j, temp;
        i = left;
        j = right;
        if (left > right)
            return;
        temp = a[left];
        while (i != j)/* 找到�?��位置 */
        {
            while (a[j] >= temp && j > i)
                j--;
            if (j > i)
                a[i++] = a[j];
            while (a[i] <= temp && j > i)
                i++;
            if (j > i)
                a[j--] = a[i];

        }
        a[i] = temp;
        quickSort(a, left, i - 1);/* 递归左边 */
        quickSort(a, i + 1, right);/* 递归右边 */
    }

    public static void quickSortMap(Map<String, Object>[] a, String fieldName, int left, int right) {
        if (fieldName != null && a != null && a.length > 0 && a[0] != null && a[0].containsKey(fieldName)) {
            int i, j;
            Map<String, Object> temp;
            i = left;
            j = right;
            if (left > right)
                return;
            temp = a[left];
            while (i != j)/* 找到�?��位置 */
            {
                while (a[j].get(fieldName).toString().compareTo(temp.get(fieldName).toString()) >= 0 && j > i)
                    j--;
                if (j > i)
                    a[i++] = a[j];
                while (a[i].get(fieldName).toString().compareTo(temp.get(fieldName).toString()) <= 0 && j > i)
                    i++;
                if (j > i)
                    a[j--] = a[i];

            }
            a[i] = temp;
            quickSortMap(a, fieldName, left, i - 1);/* 递归左边 */
            quickSortMap(a, fieldName, i + 1, right);/* 递归右边 */
        }
    }

    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成16进制表示的字�?
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();// MD5 的计算结果是�?�� 128 位的长整数，
            // 用字节表示就�?16 个字�?
            char str[] = new char[16 * 2];// 每个字节�?16 进制表示的话，使用两个字符， �?��表示�?16
            // 进制�?�� 32 个字�?
            int k = 0;// 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {// 从第�?��字节�?��，对 MD5 的每�?��字节// 转换�?16
                // 进制字符的转�?
                byte byte0 = tmp[i];// 取第 i 个字�?
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中�?4 位的数字转换,// >>>
                // 为�?辑右移，将符号位�?��右移
                str[k++] = hexDigits[byte0 & 0xf];// 取字节中�?4 位的数字转换

            }
            s = new String(str);// 换后的结果转换为字符�?

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    public static Properties loadProperties(String fileName) {
        try {
            InputStream fis = SystemUtil.class.getClassLoader().getResourceAsStream(fileName);
            if (fis == null)
                fis = SystemUtil.class.getResourceAsStream(fileName);
            Properties pro = new Properties();
            pro.load(fis);
            fis.close();
            return pro;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCreateTimeData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    // 使得日起�?��1个礼�?
    public static String getAfterOneWeekTimeData(int days) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        long enddate = date.getTime() + (days * 24 * 60 * 60 * 1000);
        date.setTime(enddate);
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static Map<String, Object> getStringInfoMap(String description, String manual, Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("description", description);
        map.put("manual", manual);
        map.put("result", object);
        return map;
    }

    // 发�?邮箱验证加密解密�?
    // ========================================================================================
    private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26,
            27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
            -1, -1, -1 };

    public static String stringEncode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    public static byte[] stringDecodeBy(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {

            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1)
                break;

            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1)
                break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            do {
                b3 = data[i++];
                if (b3 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1)
                break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            do {
                b4 = data[i++];
                if (b4 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1)
                break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }

    public static String stringDecode(String str) throws UnsupportedEncodingException {
        String e = new String(stringDecodeBy(str));
        return e;
    }

    /**
     * 创建指定数量的随机字符串
     * 
     * @param numberFlag
     *            是否是数字
     * @param length
     * @return
     */
    public synchronized static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    public static String filterSpecialSymbol(String uuid, boolean hasSpecial) {
        if (hasSpecial) {
            return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
                    + uuid.substring(24);
        } else {

            if (uuid == null || uuid.lastIndexOf("-") > 0 || uuid.length() < 23)
                return uuid;
            StringBuffer buffer = new StringBuffer(uuid);
            String symbol = "-";
            buffer.insert(8, symbol);
            buffer.insert(13, symbol);
            buffer.insert(18, symbol);
            buffer.insert(23, symbol);
            return buffer.toString();
        }
    }

    /**
     * Calculate password hash
     *
     * @param password
     *            password string
     * @return hash string
     */
    public static String hashPassword(String password) {
        if (password == null) {
            return null;
        }
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return SystemUtil.stringEncode(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String str) {
        try {
            return java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 数字金额大写转换
     */
    public static String digitUppercase(double n) {
        String fraction[] = { "角", "分" };
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

        String head = n < 0 ? "负" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            double f1 = new BigDecimal(n).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(10 * Math.pow(10, i))).doubleValue();
            s += (digit[(int) (Math.floor(f1) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int) Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head
                + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零")
                        .replaceAll("^整$", "零元整");
    }

    /**
     * 给字符串补位
     * 
     * @param str
     *            当前字符串
     * @param j
     *            总长度
     * @return
     */
    public static String leftFillMethod(String str, int j) {
        if (j > str.length())
            j = j - str.length();
        String nstr = "";
        for (int i = 0; i < j; i++)
            nstr += "0";
        nstr = nstr + str;
        return nstr;
    }

    public static String formartTelNum(String tel) {
        StringBuffer buffer = new StringBuffer(tel);
        if (buffer.length() < 7) {
            return buffer.toString();
        }
        buffer.insert(7, ' ');
        buffer.insert(3, ' ');
        return buffer.toString();
    }

    private final static String telreg = "^1[3|4|5|6|7|8][0-9]{9}$";

    /**
     * 是否是手机号
     * 
     * @param invitecode
     * @return
     */
    public static boolean isTelNum(String invitecode) {
        Matcher m = Pattern.compile(telreg).matcher(invitecode);
        return m.find();
    }

    /**
     * sql分页算法
     * 
     * @param page
     * @param size
     * @return 起始记录
     */
    public static int getDBFrom(int page, int size) {
        return (page - 1) * size;
    }
}
