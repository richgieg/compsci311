import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyIO {

    public static List<byte[]> readSurveysFromBinaryFile(String filePath) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(filePath))) {
            // Read the dimensions of the "2D array."
            int numRows = inputStream.readInt();
            int numCols = inputStream.readInt();

            List<byte[]> result = new ArrayList<>();

            // Read the bytes into our List<byte[]> structure.
            for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
                byte[] row = new byte[numCols];
                for (byte colIdx = 0; colIdx < numCols; colIdx++) {
                    row[colIdx] = inputStream.readByte();
                }
                result.add(row);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeSurveysToBinaryFile(List<byte[]> data, String filePath) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filePath))) {
            // Write the dimensions of the "2D array."
            int numRows = data.size();
            int numCols = (numRows > 0) ? data.get(0).length : 0;
            outputStream.writeInt(numRows);
            outputStream.writeInt(numCols);

            // Write the List<byte[]> data.
            for (byte[] row : data) {
                for (byte value : row) {
                    outputStream.writeByte(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
