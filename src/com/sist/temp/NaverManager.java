package com.sist.temp;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverManager {
	NaverDAO dao = new NaverDAO();
	int count = 0;

	public static void main(String[] arg) {
		NaverManager nm = new NaverManager();
		/*
		 * List<String> list=nm.getMovieCodeData(); int i=0; for(String
		 * code:list) { System.out.println(i+":"+code); i++; }
		 */
		List<NaverMovieVO> list = nm.getMovieInfoData();

	}

	// 1. code 얻기
	public List<String> getMovieCodeData() {
		List<String> list = new ArrayList<String>();
		try {

			for (int j = 1; j <= 12; j++) {

				Document doc = Jsoup
						.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20170601&page=" + j)
						.get();
				Elements codes = doc.select("div.tit5 a");
				for (int i = 0; i < codes.size(); i++) {
					Element atag = codes.get(i);
					String href = atag.attr("href");
					// System.out.println("http://movie.naver.com"+href);
					list.add(href.substring(href.lastIndexOf("=") + 1));
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}

	// data 얻기
	public List<NaverMovieVO> getMovieInfoData() {
		List<NaverMovieVO> list = new ArrayList<NaverMovieVO>();
		try {
			List<String> cList = getMovieCodeData();

			for (int i = count; i < cList.size(); i++) {
				/*
				 * for (int i = 0; i < cList.size(); i++) { if (i == 166 || i ==
				 * 210 || i == 213 || i == 245 || i == 394 || i == 430 || i ==
				 * 539 || i == 550 || i == 559 || i == 582) continue;재귀호출로 넘어가자
				 */
				String code = cList.get(i);
				Document doc = Jsoup.connect("http://movie.naver.com/movie/bi/mi/basic.nhn?code=" + code).get();

				Element title = doc.select("div.mv_info h3.h_movie a").first();

				Element poster = doc.select("div.poster img").first();

				Element director = doc.select("div.info_spec2 dl.step1 dd a").first();
				Element actor = doc.select("div.info_spec2 dl.step2 dd a").first();

				Element story = doc.select("div.story_area p.con_tx").first();

				System.out.println(i + ":" + title.text() + " " + director.text() + " " + actor.text() + " "
						+ poster.attr("src") + " " + story.text());
				NaverMovieVO vo = new NaverMovieVO();
				vo.setCode(Integer.parseInt(code));
				vo.setTitle(title.text());
				vo.setPoster(poster.attr("src"));
				vo.setDirector(director.text());
				vo.setActor(actor.text());
				String str = story.text();
				str = str.replace("'", "");
				str = str.replace("\"", "");
				vo.setStory(str);
				dao.movieInsert(vo);

				count++;

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			count++;
			this.getMovieInfoData();
		} finally {

		}
		return list;
	}
}
