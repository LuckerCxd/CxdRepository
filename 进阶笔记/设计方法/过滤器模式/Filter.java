package Try_sig.Fac;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {
	public static void main(String[] args) {
		Dog[] arrayDogs = new Dog[] {new Dog("Wild", "Male")
		,new Dog("Dometicate", "FeMale")
		,new Dog("Dometicate", "feMale")
		,new Dog("Dometicate", "Male")
		,new Dog("Wild", "feMale")
		,new Dog("Dometicate", "feMale")
		,new Dog("Dometicate", "Male")
		,new Dog("Wild", "feMale")};
		List<Dog> dogs = Arrays.asList(arrayDogs);
		Selected selected = new AndSelcted(new SelectedFemale(), new SelectedWild());	
		prinList(selected.selectDogs(dogs));
	
		selected = new NotSelected(new SelectedFemale());
		prinList(selected.selectDogs(dogs));
	}
	public static void prinList(List<Dog> list) {
		for(Dog dog: list) {
			System.out.println("dog 's born : "+dog.getBorn()+"  dog 's sexual: "+dog.getSexual());
		}
	}
}
class Dog{
	private String born;
	public String getSexual() {
		return sexual;
	}
	private String sexual;
	public String getBorn() {
		return born;
	}
	public Dog(String born, String sexual) {
		this.born = born;
		this.sexual = sexual;
	}
}
class SelectedFemale implements Selected{
	@Override
	public List<Dog> selectDogs(List<Dog> dogs) {
		List<Dog>list = new ArrayList<Dog>();
		for(Dog dog: dogs) {
			if(dog.getSexual().equalsIgnoreCase("Female")) {
				list.add(dog);
			}
		}
		return list;
	}
}
class SelectedWild implements Selected{
	@Override
	public List<Dog> selectDogs(List<Dog> dogs) {
		List<Dog>list = new ArrayList<Dog>();
		for(Dog dog: dogs) {
			if(dog.getBorn().equalsIgnoreCase("Wild"))
				list.add(dog);
		}
		return list;
	}
}
class AndSelcted implements Selected{
	private Selected firstSelect;
	private Selected secondSelect;
	public AndSelcted(Selected first,Selected second) {
		firstSelect = first;
		secondSelect = second;
	}
	@Override
	public List<Dog> selectDogs(List<Dog> dogs) {
		List<Dog> list = firstSelect.selectDogs(dogs);
		return secondSelect.selectDogs(list);
	}
}

class ORSelcted implements Selected{
	private Selected firstSelect;
	private Selected secondSelect;
	public ORSelcted(Selected first,Selected second) {
		firstSelect = first;
		secondSelect = second;
	}
	@Override
	public List<Dog> selectDogs(List<Dog> dogs) {
		List<Dog> list = firstSelect.selectDogs(dogs);
		List<Dog> list2 = secondSelect.selectDogs(dogs);
		for(Dog dog:list2) {
			if(list.contains(dog) == false) {
				list.add(dog);
			}
		}
		return list;
	}
}
class NotSelected implements Selected{
	private Selected firstSelect;
	public NotSelected(Selected first) {
		firstSelect = first;
	}
	@Override
	public List<Dog> selectDogs(List<Dog> dogs) {
		List<Dog>list = firstSelect.selectDogs(dogs);
		List<Dog>list2 = new ArrayList<Dog>();
		for(Dog dog: dogs) {
			if(list.contains(dog) == false)
				list2.add(dog);
		}
		return list2;
	}
}

