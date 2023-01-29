package com.newlecture.app.console;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {

    private NoticeService service;
    private int page;
    private String searchField;
    private String searchWord;
    private int showDetails;

    public NoticeConsole() {
        this.service = new NoticeService();
        this.page = 1;
        this.searchField = "TITLE";
        this.searchWord = "";
        this.showDetails = 0;
    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list = service.getList(page, searchField, searchWord);
        int count = service.getCount(); // 게시글 개수
        int lastPage = count / 10;    // 100 -> 10, 90 -> 9, 93/10 -> 9?
        lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;

        System.out.println("──────────────────────────────────────");
        System.out.printf("       <공지사항> 총 %d 게시글\n", count);
        System.out.println("──────────────────────────────────────");

        for (Notice n : list) {
            System.out.printf("%d. %s / %s / %s\n", n.getId(), n.getTitle(), n.getWriterId(), n.getRegDate());
        }
        System.out.println("──────────────────────────────────────");
        System.out.printf("             %d/%d pages\n", page, lastPage);
    }

    public int inputNoticeMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.print("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.글수정 / 6.글삭제 / 7.검색 / 8.종료 > ");
        String menu_ = sc.nextLine();
        int menu = Integer.parseInt(menu_);

        return menu;
    }

    public void movePrevList() {
        if (page == 1) {
            System.out.println("\n\n========================");
            System.out.println("[ 이전 페이지가 없습니다. ]");
            System.out.println("========================\n\n");
            return;
        }
        page--;
    }

    public void moveNextList() throws SQLException, ClassNotFoundException {
        int count = service.getCount(); // 게시글 개수
        int lastPage = count / 10;    // 100 -> 10, 90 -> 9, 93/10 -> 9?
        lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;

        if (page == lastPage) {
            System.out.println("\n\n========================");
            System.out.println("[ 다음 페이지가 없습니다. ]");
            System.out.println("========================\n\n");
            return;
        }
        page++;
    }

    public void inputSearchWord() {
        Scanner sc = new Scanner(System.in);

        System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요.");
        System.out.print(">");
        searchField = sc.nextLine();
        if (searchField.equalsIgnoreCase("writerId")) {
            searchField = "writer_id";
        }

        System.out.print("검색어 > ");
        searchWord = sc.nextLine();
    }

    public void details() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("상세보기를 원하는 글 번호를 입력해주세요. > ");
        showDetails = sc.nextInt();

        List<Notice> list = service.getList(page, searchField, searchWord);
        for (Notice n : list) {
            if (n.getId() == showDetails) {
                System.out.printf("────────────────────<<%d번 게시글>>──────────────────\n", n.getId());
                System.out.printf(" 제목 : %s | 작성자 : %s | 조회수 : %d\n", n.getTitle(), n.getWriterId(), n.getHit());
                System.out.printf("────────────────────<<본문내용>>────────────────────\n");
                for (int i = 0; i < n.getContent().length(); i++) {
                    System.out.print(n.getContent().charAt(i));
                    if (i % 30 == 29) {
                        System.out.println();
                    }
                }
                System.out.println("\n───────────────────────────────────────────────────\n\n");
            }
        }
    }

    public void write() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("제목을 입력해주세요. > ");
        String title = sc.nextLine();
        System.out.print("작성자를 입력해주세요. > ");
        String writerId = sc.nextLine();
        System.out.print("내용을 입력해주세요. > ");
        String content = sc.nextLine();
        System.out.print("파일을 추가해주세요. > ");
        String files = sc.nextLine();

        Notice newNotice = new Notice(title, writerId, content, files);
        service.insert(newNotice);
    }

    public void modify() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 게시글 번호를 입력해주세요. > ");
        int modifyNumber = sc.nextInt();
        sc.nextLine();
        List<Notice> list = service.getList(page, searchField, searchWord);
        for (Notice modifiedNotice : list) {
            if (modifiedNotice.getId() == modifyNumber) {
                System.out.print("수정할 제목을 입력해주세요. > ");
                modifiedNotice.setTitle(sc.nextLine());
                System.out.print("수정할 내용을 입력해주세요. > ");
                modifiedNotice.setContent(sc.nextLine());
                System.out.print("수정할 파일을 입력해주세요. > ");
                modifiedNotice.setFiles(sc.nextLine());
                System.out.printf("정말 %d번 게시글을 수정하시겠습니까? (Y/N) > ", modifyNumber);
                char yesOrNo = sc.nextLine().charAt(0);
                if(Character.toUpperCase(yesOrNo) == 'Y') {
                    service.update(modifiedNotice);
                    System.out.println("       <<수정 완료>>");
                    return;
                } else if(Character.toUpperCase(yesOrNo) == 'N'){
                    System.out.println("수정을 취소하셨습니다.");
                    return;
                } else {
                    System.out.println("잘못된 입력값입니다. !! Y 또는 N을 입력해주세요 !!");
                }
            }
        }
        System.out.println("입력하신 게시글 번호가 존재하지 않습니다.");
    }

    public void remove() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 게시글 번호를 입력해주세요. > ");
        int removeNumber = sc.nextInt();
        sc.nextLine();
        List<Notice> list = service.getList(page, searchField, searchWord);
        for(Notice deleteNotice : list) {
            if(deleteNotice.getId() == removeNumber) {
                System.out.printf("정말 %d번 게시글을 삭제하시겠습니까? (Y/N) > ", removeNumber);
                char yesOrNo = sc.nextLine().charAt(0);
                if(Character.toUpperCase(yesOrNo) == 'Y') {
                    service.delete(removeNumber);
                    System.out.println("       <<삭제 완료>>");
                    return;
                } else if(Character.toUpperCase(yesOrNo) == 'N'){
                    System.out.println("삭제를 취소하셨습니다.");
                    return;
                } else {
                    System.out.println("잘못된 입력값입니다. !! Y 또는 N을 입력해주세요 !!");
                    return;
                }
            }
        }
        System.out.println("입력하신 게시글 번호가 존재하지 않습니다.");
    }
}
