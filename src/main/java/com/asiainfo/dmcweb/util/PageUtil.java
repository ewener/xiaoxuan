package com.asiainfo.dmcweb.util;

public class PageUtil {
	
		private int start;
		private int end;
		
		private PageUtil(int pageNumber,int pageSize){
			iniPageInfo(pageNumber, pageSize);
		}
		
		public static  PageUtil getPage(int pageNumber,int pageSize){
			return new PageUtil(pageNumber,pageSize);
		}
		
		public void iniPageInfo(int pageNumber,int pageSize){
			int start = (pageNumber-1)*pageSize; 
			int end = start+pageSize+1;
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}
		
		public static void main(String[] args) {
			PageUtil page = getPage(1,10);
			System.out.println(page.getStart()+" "+page.getEnd() );
		}
}
