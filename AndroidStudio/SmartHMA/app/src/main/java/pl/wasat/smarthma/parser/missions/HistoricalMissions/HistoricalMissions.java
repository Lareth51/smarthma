package pl.wasat.smarthma.parser.missions.HistoricalMissions;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-13.
 */
public class HistoricalMissions extends BaseParser implements SimpleMissionInterface {
	final int ITEMS_COUNT = 9;
	public final static int CATEGORY_ID = 3;


	public HistoricalMissions(String pageUrl, Context context) {
		super(pageUrl, context);

	}

	@Override
	public void mainContent() {
		Pair pair = getImageListPage(ITEMS_COUNT, false);
		String contents = super.imageListToContentString(pair);
		parserDb.addCategory(new Category(CATEGORY_ID, (String)pair.title, contents));
		ArrayList<String> urlList = super.getImageList((ArrayList<String>)pair.content);
		System.out.println(urlList.size());
		int mission_id = 43;
		String img_name= "MISSION_IMG";
		for (String url :
				urlList) {
			parserDb.addPage(new Page(CATEGORY_ID, mission_id, img_name, url));
			mission_id++;
		}
	}

	public Pair<String, ArrayList<String>> getImageListPage(String pageName, int maxItems,  boolean fullPage){

		String title;
		ArrayList<String> contentList = new ArrayList<>();
		Document doc;

		try{
			if(pageName.equals("")){
				if(fullPage){
					doc = Jsoup.connect(pageUrl + "/" + getAll).get();
				}
				else{
					doc = Jsoup.connect(pageUrl).get();
				}
			}
			else{
				if(fullPage){
					doc = Jsoup.connect(pageUrl + "/" + pageName + getAll).get();
				}
				else{
					doc = Jsoup.connect(pageUrl + "/" + pageName).get();
				}
			}
			title = doc.select(titleClass).first().text();
			Elements contents = doc.select(imageListClass);
			Log.d("imgListSize", String.valueOf(contents.size()));
			for(int i=1;i<contents.size();i++){
				contentList.add(contents.get(i).html());

				//System.out.println(getImageSource(content.html()));
				if(contentList.size() >= maxItems){
					break;
				}
			}
		}catch(IOException e){
			Log.d(BaseParser.class.toString(), e.toString());
			title = "";
		}
		return new Pair<>(title, contentList);
	}
}
