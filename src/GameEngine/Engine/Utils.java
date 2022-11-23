package GameEngine.Engine;

import org.joml.Vector2d;
import org.joml.Vector3d;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class    Utils {

    public static String loadResource(String fileName) throws Exception {
        String result;
        try (InputStream in = Utils.class.getResourceAsStream(fileName);
             Scanner scanner = new Scanner(in, java.nio.charset.StandardCharsets.UTF_8.name())) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
    public static ArrayList<Vector3d> CreateCurveBezier(ArrayList<Vector3d> positions){
        ArrayList<Vector3d> _vertices_bezier = new ArrayList<>();
        ArrayList<Integer> pascal = getRow(positions.size() - 1);
        for(float t = 0;t<=1.0f;t+=0.01f){
            Vector2d p = getP(positions.size(),t,positions,pascal);
            _vertices_bezier.add(new Vector3d(p.x,p.y,0));
        }
        return  _vertices_bezier;
    }
    public static ArrayList<Integer> getRow(Integer rowIndex){
        ArrayList<Integer> currow = new ArrayList<>();
        currow.add(1);
        if(rowIndex == 0){
            return currow;
        }
        ArrayList<Integer> prev = getRow(rowIndex-1);
        for(int i = 1;i< prev.size();i++){
            int curr = prev.get(i-1)+prev.get(i);
            currow.add(curr);
        }
        currow.add(1);
        return currow;
    }
    public static Vector2d getP(int n,float t, ArrayList<Vector3d> positions,ArrayList<Integer> _pascal){
        Vector2d p = new Vector2d(0,0);
        float[] k = new float[n];
        for(int i = 0;i<n;i++){
            k[i] = (float)Math.pow((1 - t), n - 1 - i) * (float)Math.pow(t, i) * _pascal.get(i);
        }
        for(int i = 0;i<n;i++){
            p.x += k[i] * positions.get(i).x;
            p.y += k[i] * positions.get(i).y;
        }
        return p;
    }

    public static ArrayList<Vector3d> createCircle(Vector3d position, double radius, int lod) {
        ArrayList<Vector3d> vertices = new ArrayList<>();

        for (int i = 0; i < lod; i++) {
            vertices.add(new Vector3d(
                    Math.cos(2 * Math.PI / lod * i) * radius + position.x,
                    Math.sin(2 * Math.PI / lod * i) * radius + position.y,
                    0.0));
        }

        return vertices;
    }
    public static ArrayList<Vector3d> CreateLine(Vector3d positionA, Vector3d positionB){
        ArrayList<Vector3d> vertices = new ArrayList<>();
        for(double t = 0;t<=1;t+=0.1){
            Vector3d tempA = new Vector3d(positionA);
            Vector3d tempB = new Vector3d(positionB);
            Vector3d tempC = new Vector3d();
            Vector3d math = tempA.mul(1-t).add(tempB.mul(t),tempC);
            vertices.add(math);
        }
        return vertices;
    }
    public static List<String> readAllLines(String fileName) throws Exception {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Class.forName(Utils.class.getName()).getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }
}
