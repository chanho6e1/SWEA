import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;

public class SWEA_4012_요리사 {
	
	static int n, r, map[][], min;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			r = n/2;
			
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// A음식과 B음식의 맛 차이가 최소가 되도록
			min = Integer.MAX_VALUE;
			visited = new boolean[n];
			Comb(0, 0);
			
			sb.append("#" + t + " " + min + '\n');
		}

		System.out.println(sb);
	}
	
	static void Comb(int idx, int start) {
		if(idx == r) {
			int sumA = 0, sumB = 0;
			
			for(int i=0; i<n-1; i++) {
				for(int j=i+1; j<n; j++) {
					if(visited[i] && visited[j])
						sumA += map[i][j] + map[j][i];
					if(!visited[i] && !visited[j])
						sumB += map[i][j] + map[j][i];
				}
			}
			
			min = Math.min(min, Math.abs(sumA - sumB));
			return;
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
			Comb(idx+1, i+1);
			visited[i] = false;
		}
		
	}

}
