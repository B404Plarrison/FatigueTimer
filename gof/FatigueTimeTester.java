import java.util.Scanner;
public class FatigueTimeTester {
	public double current=System.nanoTime()/1E9;
	public double mult=0;
	public double total=0;
	public boolean active=true;
	public static Scanner in=new Scanner(System.in);
	 public FatigueTimeTester() {
	 
	 }
	 public FatigueTimeTester(double begin) {
		 current=begin;
	 }
	 public FatigueTimeTester(double begin, double multd) {
		 current=begin;
		 mult=multd;
	 }
	 public void deactivate() {
		 active=false;
	 }
	 public void activate() {
		 active=true;
	 }
	 public void heel() {
		 current=System.nanoTime()/1E9;
	 }
	 public void setState(boolean state) {
		 this.active=state;
	 }
	 public void addMult(double multa) {
		 this.total+=mult*((System.nanoTime()/1E9)-current);
		 current=System.nanoTime()/1E9;
		 this.mult+=multa;
	 }
	 public void subMult(double multa) {
		 this.total+=mult*((System.nanoTime()/1E9)-current);
		 current=System.nanoTime()/1E9;
		 this.mult-=multa;
	 }
	 public void setMult(double multa) {
		 this.total+=mult*((System.nanoTime()/1E9)-current);
		 current=System.nanoTime()/1E9;
		 this.mult=multa;
	 }
	 public double getTotal() {
		this.total+=mult*((System.nanoTime()/1E9)-current);
		current=System.nanoTime()/1E9;
		return total;
	 }
	 public static void main(String[] good) throws Exception {
		 
		 FatigueTimeTester silasacid=new FatigueTimeTester();
		 while(true) {
		 System.out.println("more stuff");
		 double input=in.nextDouble();
		 silasacid.addMult(input);
		 System.out.println(silasacid.getTotal());
	 }
	 }
}
