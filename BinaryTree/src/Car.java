
public class Car implements Comparable<Car> {
	private int model;

	public Car(int m) {
		model = m;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "car-"+model;
	}
	
	@Override
	public int compareTo(Car o) {
		// TODO Auto-generated method stub
		if (this.model == o.model) {
			return 0;
		} else if (this.model < o.model) {
			return -1;
		} else if (this.model > o.model) {
			return 1;
		}
		return 0;
	}
}
