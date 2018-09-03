//package jsoap;
//
//import java.util.List;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//
//
//public class DealUrl implements Runnable {
//
//	  // �ѽ���url����
//    private List<String> visited = null;
//    // δ����url����
//    private List<String> hrefs = null;
//    // ͼƬ���Ӷ���
//    private List<String> images = null;
//     
//    //�ѽ���������
//    private int analyze = 0;
//    private int count   = 0;        
//    public DealUrl(List<String> hrefs, List<String> visited, List<String> images) {
//        this.hrefs = hrefs;
//        this.visited = visited;
//        this.images = images;
//    }
// 
//    public void run() {
//        while (!hrefs.isEmpty()) {
//            // �ѵ�ǰҪ������url�ַ�����hrefs�Ƶ�visited
//            String urlTmp = hrefs.remove(0);
//            if (visited.indexOf(urlTmp) != -1)
//                continue;
//            visited.add(urlTmp);
//            Document doc = getUrlDoc((String) visited.get(visited.size() - 1));
//            if (doc == null)
//                continue;
//            System.out.println("�ѽ����� " + ++analyze + " �����ӡ�����"+urlTmp);
//            Elements hrefLinks = doc.select("a[href]");
//            Elements imgLinks = doc.select("img[src]");
//            if (hrefLinks != null)
//                for (Element link : hrefLinks) {
//                    String newUrl = link.attr("abs:href");
//                    if (newUrl.indexOf("ququ") != -1)
//                        hrefs.add(newUrl);
//                    // System.out.println(++count + "  >>> " +
//                    // link.attr("abs:href"));
//                }
//            if (imgLinks == null)
//                continue;
//            for (Element link : imgLinks) {
//                String temImgUrl = link.attr("abs:src");
//                if (temImgUrl.indexOf(".jpg") != -1 && images.indexOf(temImgUrl) == -1) {
//                    images.add(link.attr("abs:src"));
//                    System.out.println("img:"+link.attr("abs:src"));
//                }
//            }
//             new Thread(new DownloadImage(images)).start();
//        }
//        System.gc();
//    }
//    public Document getUrlDoc(String url){
//        Document doc = null;
//        try {
//            new UseProxy();//���Ǵ��������Ŀ���ע�͵�
//            Connection conneciton = Jsoup.connect(url);
//            conneciton.userAgent(Constant.AGENT);
//            doc = conneciton.get();
//        } catch (Exception e) {
//            System.out.println("connect fail!");
//                        return null;
//        }
//        return doc;
//    }
//
//}
