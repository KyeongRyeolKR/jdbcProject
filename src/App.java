import com.newlecture.app.console.NoticeConsole;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        NoticeConsole console = new NoticeConsole();
        EXIT:
        while (true) {
            console.printNoticeList();
            int menu = console.inputNoticeMenu();
            System.out.println("\n\n");

            switch (menu) {
                case 1: // 상세조회
                    console.details();
                    break;
                case 2: // 이전
                    console.movePrevList();
                    break;
                case 3: // 다음
                    console.moveNextList();
                    break;
                case 4: // 글쓰기
                    console.write();
                    break;
                case 5: // 글수정
                    console.modify();
                    break;
                case 6: // 글삭제
                    console.remove();
                    break;
                case 7: // 검색
                    console.inputSearchWord();
                    break;
                case 8: // 종료
                    System.out.println("Bye~~");
                    break EXIT;
                default:
                    System.out.println("<<사용방법>> 메뉴는 1~8까지만 입력할 수 있습니다.");
                    break;
            }
        }

    }
}
