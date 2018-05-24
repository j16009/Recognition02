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

			String name = node.get("images").get(0).get("classifiers").get(0).get("name").asText();
			System.out.println("name : " + name);

			String classifier_id = node.get("images").get(0).get("classifiers").get(0).get("classifier_id").asText();
			System.out.println("classifier_id : " + classifier_id);

			String classes_class0 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").asText();
			System.out.println("classes_class0 : " + classes_class0);

			String classes_score0 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score").asText();
			System.out.println("classes_score0 : " + classes_score0);

			String classes_class1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class").asText();
			System.out.println("classes_class1 : " + classes_class1);

			String classes_score1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score").asText();
			System.out.println("classes_score1 : " + classes_score1);

			String classes_class2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class").asText();
			System.out.println("classes_class2 : " + classes_class2);

			String classes_score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score").asText();
			System.out.println("classes_score2 : " + classes_score2);


		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

	}
}
