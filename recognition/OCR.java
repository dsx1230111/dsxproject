package recognition;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;

public class OCR {
	/**
	 *
	 * @param srImage
	 *            图片路径
	 * @param ZH_CN
	 *            是否使用中文训练库,true-是
	 * @return 识别结果
	 */
	public static String FindOCR(String srImage, boolean ZH_CN) {
		try {
			System.out.println("start");
			double start = System.currentTimeMillis();
			File imageFile = new File(srImage);
			if (!imageFile.exists()) {
				return "图片不存在";
			}
			BufferedImage textImage = ImageIO.read(imageFile);
			Tesseract instance = new Tesseract();
			// 设置训练库
			instance.setDatapath("D:\\projectSpace\\pay\\tessdata");
			if (ZH_CN)
				// 中文识别
				instance.setLanguage("chi_sim");
			String result = null;
			result = instance.doOCR(textImage);
			double end = System.currentTimeMillis();
			System.out.println("耗时" + (end - start) / 1000 + " s");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "发生未知错误";
		}
	}
}
