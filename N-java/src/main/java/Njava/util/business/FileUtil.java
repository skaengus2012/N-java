package Njava.util.business;

import Njava.modeler.NxModeler;
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
        return CreateFile(filePath).exists();
    }

    /**
     * 파일 객체를 생성한다.
     *
     * @param filePath
     * @return
     */
    @NonNull
    public static File CreateFile(@NonNull String filePath) {
        NxModeler.NullCheck(filePath);

        return new File(filePath);
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
