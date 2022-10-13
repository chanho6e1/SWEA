import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로 {

	static int N, X, map[][], map2[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			map2 = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			// 활주로 동일한 높이 구간에서 건설 가능
			// 경사로의 길이x, 높이1
			// 경사로는 높이 차이가 1이고 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치 가능

			System.out.println("#" + t + " " + process());
		}

	}

	private static int process() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			if (makeRoad(map[i]))
				cnt++;
			if (makeRoad(map2[i]))
				cnt++;
		}

		return cnt;
	}

	private static boolean makeRoad(int[] road) {
		
		int beforeH = road[0], size = 0;
		int j = 0;
		
		while(j<N) {
			if(beforeH == road[j]) {
				size++;
				j++;
			}else if(beforeH+1 == road[j]) { // 이전 높이보다 1 클 때
				if(size<X) return false;
				
				beforeH++;
				size=1;
				j++;
			}else if(beforeH-1 == road[j]) {
				int cnt = 0;
				for(int k=j; k<N; k++) {
					if(road[k]!=beforeH-1) return false;
					if(++cnt == X) break;
				}
				
				if(cnt<X) return false;
				
				beforeH--;
				j+=X;
				size = 0;
			}else {
				return false;
			}
		}
		
		return true;
	}

}
