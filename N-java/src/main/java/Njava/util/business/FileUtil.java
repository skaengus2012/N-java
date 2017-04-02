package Njava.util.business;

import Njava.function.exceptionLambda.IExBiFunction;
import Njava.function.exceptionLambda.IExConsumer;
import Njava.function.exceptionLambda.IExFunction;
import Njava.function.exceptionLambda.IExPredicate;
import Njava.modeler.NxModeler;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * File 관련 프로세싱 유틸.
 * <p>
 * Created by Doohyun on 2017. 3. 29..
 */
public class FileUtil {

    /**
     * 파일 존재 여부
     *
     * @param filePath
     * @return
     */
    @NonNull
    public static boolean IsExist(@NonNull String filePath) {
        return IsExist(CreateFileObject(filePath));
    }

    /**
     * 파일 존재 여부
     *
     * @param file
     * @return
     */
    @NonNull
    public static boolean IsExist(@NonNull File file) {
        return file.exists();
    }

    /**
     * 디렉토리 여부 확인.
     *
     * @param filePath
     * @return
     */
    @NonNull
    public static boolean IsExistDirectory(@NonNull String filePath) {
        return IsExistDirectory(CreateFileObject(filePath));
    }

    /**
     * 디렉토리 여부 확인.
     *
     * @param file
     * @return
     */
    @NonNull
    public static boolean IsExistDirectory(@NonNull File file) {
        return file.exists() && file.isDirectory();
    }

    /**
     * 파일 이름 객체가 들어간 경로에 대해서 파일 path 를 조회한다.
     *
     * @param pullFilePath
     * @return
     */
    public static String GetDirectoryPath(@NonNull String pullFilePath) {
        final String[] splitList = pullFilePath.split("/");

        String result;

        if (splitList.length == 0) {
            result = "";
        } else {
            result =  Observable.range(0, splitList.length - 1).map(new IExFunction<Integer, String>() {
                @Override
                public String apply(@NonNull Integer index) throws Exception {
                    return splitList[index];
                }
            }).reduce(new StringBuffer(""), new IExBiFunction<StringBuffer, String, StringBuffer>() {
                @Override
                public StringBuffer apply(@NonNull StringBuffer result, @NonNull String folder) throws Exception {
                    return result.append(folder).append("/");
                }
            }).blockingGet().toString();
        }

        // 파일 주소가 비어있다면, '/' 를 추가한다.
        if (StringUtil.IsEmpty(result)) {
            result = "/";
        }

        return result;
    }

    /**
     * 파일 객체를 생성한다.
     *
     * @param filePath
     * @return
     */
    @NonNull
    public static File CreateFileObject(@NonNull String filePath) {
        NxModeler.NullCheck(filePath);

        return new File(filePath);
    }

    /**
     * 파일을 생성한다.
     *
     * <pre>
     *     내부 폴더가 없다면 내부 폴더를 생성한 후, 파일을 제작한다.
     * </pre>
     *
     * @param filePath
     * @throws IOException
     */
    public static void CreateFile(@NonNull String filePath) throws IOException{
        NxModeler.NullCheck(filePath);

        File file = CreateFileObject(filePath);

        // 파일 폴더의 존재유무 확인, 없다면 생성할 것.
        CreateDirectory(GetDirectoryPath(filePath));

        file.createNewFile();
    }

    /**
     * 파일 객체를 받아 파일을 생성한다.
     *
     * @param file
     * @throws IOException
     */
    public static void CreateFile(@NonNull File file) throws IOException{
        NxModeler.NullCheck(file);

        CreateFile(file.getPath());
    }

    /**
     * 디렉토리 생성 정의.
     *
     * @param filePath
     */
    public static void CreateDirectory(@NonNull final String filePath) {
        NxModeler.NullCheck(filePath);

        File directory = CreateFileObject(filePath);

        Maybe.just(directory).filter(new IExPredicate<File>() {
            @Override
            public boolean test(@NonNull File file) throws Exception {
                // 폴더로 존재하지 않을 경우.
                return !IsExistDirectory(file);
            }
        }).subscribe(new IExConsumer<File>() {
            @Override
            public void accept(@NonNull File file) throws Exception {
                // 디렉토리를 생성한다.
                file.mkdirs();
            }
        });
    }

    /**
     * 마지막 파일의 문장을 삭제하고, 폴더를 생성한다.
     *
     * @param filePath
     */
    public static void CreateDirectoryExcludeLastPath(@NonNull String filePath) {
        NxModeler.NullCheck(filePath);

        // 마지막 파일 상태를 제외한다.
        String excludeLastPath = GetDirectoryPath(filePath);

        CreateDirectory(excludeLastPath);
    }

    /**
     * 디렉토리 패스를 가진 File Object 을 받아 파일을 생성한다.
     *
     * @param directory
     */
    public static void CreateDirectory(@NonNull final File directory) {
        NxModeler.NullCheck(directory);

        CreateDirectory(directory.getPath());
    }

    /**
     * 파일을 삭제한다.
     *
     * @param filePath
     */
    public static void DeleteFile(@NonNull String filePath) {
        NxModeler.NullCheck(filePath);

        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 파일을 Copy 한다.
     *
     * @param inFileName
     * @param outFileName
     * @throws IOException
     */
    public static void CopyFile(@NonNull String inFileName, @NonNull String outFileName) throws IOException {

        NxModeler.NullCheck(inFileName);
        NxModeler.NullCheck(outFileName);

        FileInputStream inputStream = new FileInputStream(inFileName);
        FileOutputStream outputStream = new FileOutputStream(outFileName);

        FileChannel fcIn = inputStream.getChannel();
        FileChannel fcOut = outputStream.getChannel();

        long size = fcIn.size();
        fcIn.transferTo(0, size, fcOut);

        fcOut.close();
        fcIn.close();

        outputStream.close();
        inputStream.close();
    }
}
