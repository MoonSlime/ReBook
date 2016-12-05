package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class BookData {
  /*   private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private String link;
    private int discount;
    private int isbn;
    private Date pubdate;
*/
   /* private String title;
    private String image_url;//이미지 url
    private String author;
    private int price;
    private String publisher;
    private String description;//요약정보
*/
    private int total;
    private Item item;

    BookData(){
        item=new Item();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
      return item.getTitle();
    }

    public class Item{
        private String title;
        private String link;
        private String image;
        private String author;
        private Integer price;
        private String publisher;
        private String description;

        Item(){}

        public void setTitle(String title){
            this.title=title;
        }

        public String getTitle() {
            return title;
        }
    }
}
