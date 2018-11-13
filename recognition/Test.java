package recognition;

import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		String filePath = "E:\\test\\test.pdf";
		List<String> imageList = PdfToImagePdfBox.pdfToImagePath(filePath);
		String imagePath = filePath.substring(0, filePath.lastIndexOf(".")) + "/";
		for (int i = 0; i < imageList.size(); i++) {
			String result = OCR.FindOCR(imagePath+i+".jpg", true);
			System.out.println(result);
		}
		
	}

}
