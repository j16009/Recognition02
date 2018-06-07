package recognition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition02_main {
	public static void main(String[] args){

		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("J16009");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("img/fruits.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				.imagesFile(imagesStream)
				.imagesFilename("img/fruits.png")
				.threshold((float) 0.6)
				.owners(Arrays.asList("IBM"))
				.build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);
		String s = String.valueOf(result);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(s);

			String object = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").toString();
			System.out.println("class : " + object);
			Double object_score = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score").asDouble();
			System.out.println("class_score : " + object_score);

			String color1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class").toString();
			System.out.println("color1 : " + color1);
			Double color_score1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score").asDouble();
			System.out.println("color_score1 : " + color_score1);

			String color2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class").toString();
			System.out.println("color2 : " + color2);
			Double color_score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score").asDouble();
			System.out.println("color_score2 : " + color_score2);


			MySQL mysql = new MySQL();
			mysql.updateImage(object,object_score,color1,color_score1,color2,color_score2);


		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

	}
}
