import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by stasPC on 14.04.2017.
 */
public class FileSearcher {

    public FileSearcher() {}

    private static final List<File> fileList = new ArrayList<>();

    // Программа выполняет поиск файлов по части/полному имени и выводит краткую информацию(имя, путь, размер, последнее изменение)
    // fileSearcher(String path, String find, boolean useRegex) принимает путь, искомое выражение и useRegex параметр, который следит используете ли вы регулярные выражения; метод ходит рекурсивно по поддеректориям
    static void fileSearcher(String path, String find, boolean useRegex) {
        File file = new File(path);
        File[] list = file.listFiles();

        if (list == null) {
            return;
        }
        for (File curfile : list) {
            if (useRegex) {
                Matcher m;
                Pattern p;
                p = Pattern.compile(find);
                m = p.matcher(curfile.toString());
                if (m.matches()) fileList.add(curfile);
            } else if (curfile.getName().contains(find)) {
                fileList.add(curfile);
            }

            File tempfile = new File(path.toString() ,curfile.getName());

            if (!curfile.getName().equals(".") && !curfile.getName().equals("..")) { // смотрим, чтобы это не была данной или материнской данной папка
                if (tempfile.isDirectory()) {
                    fileSearcher(tempfile.getPath()+ "\\", find, useRegex); // рекурсия
                }
            }
        }
    }
// whatFormat(String key) метод для вывода информации (с ключом), здесь использован java formatter для реализации таблицы
    void whatFormat(String key) {

        boolean byteSize = false;
        boolean lastModified = false;

        switch (key) {

            case "@": {
                System.out.format("%-15s%-25s%-100s%-15s\n", "Directory", "Name", "Path",
                        "Byte Size");
                byteSize = true;
                break;
            }
            case "#": {
                System.out.format("%-15s%-25s%-100s%-15s%-15s\n", "Directory", "Name", "Path",
                        "Byte Size", "Last Modified");
                lastModified = true;
                break;
            }
        }

        for(File file : fileList) {

            if(byteSize){
                System.out.format("%-15s%-25s%-100s%-15s\n", file.isDirectory(),
                        file.getName(), file.getParent(), file.length());
            }
            if(lastModified){
                System.out.format("%-15s%-25s%-100s%-15s%-15s\n", file.isDirectory(),
                        file.getName(), file.getParent(), file.length(),
                        new Date(file.lastModified()));
            }
        }
    }
    // whatFormat() метод для вывода информации (БЕЗ ключа, дефолтный вывод)
    void whatFormat() {

        System.out.format("%-15s%-25s%-100s\n", "Directory", "Name", "Path");

        for(File file : fileList) {

            System.out.format("%-15s%-25s%-100s\n", file.isDirectory(),
                    file.getName(), file.getParent());

        }
    }
}
