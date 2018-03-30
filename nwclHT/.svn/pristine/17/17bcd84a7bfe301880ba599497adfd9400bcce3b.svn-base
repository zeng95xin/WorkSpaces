package com.bola.nwcl.web;



public class Test {

	public static void main(String[] args) {
		//Test t = new Test();
		int s = turns(1, 2, 3);
		System.out.println("--------------    "+s);
	}
	
	public static int turns(int n, int high, int low){
		int turns = 0;
		while(high - low <= 2){
			turns++;
			int mid = (high+low)/2;
			if (mid == n) {
				return turns;
			}else if(mid < n){
				low = mid + 1;
			}else{
				high = mid -1;
			
			}
		}
		return 1+turns;
	}
}
