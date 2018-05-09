package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

enum Gender {
	MALE, FEMALE
}

class Student {
	private String name;
	private int age;
	private Gender sex;
	private Grade grade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student(String name, int age, Gender sex, Grade grade) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.grade = grade;
	}
}

enum Grade {
	ONE, TWO, THREE, FOUR;
}

public class CoreStream {

	public static void main(String[] args) {
		List<Student> students = Arrays.asList(new Student("小明", 10, Gender.FEMALE, Grade.ONE),
				new Student("小明2", 8, Gender.MALE, Grade.TWO), new Student("小明3", 23, Gender.FEMALE, Grade.TWO),
				new Student("小明4", 12, Gender.MALE, Grade.ONE), new Student("小明5", 33, Gender.FEMALE, Grade.ONE),
				new Student("小明6", 3, Gender.MALE, Grade.ONE));

		//
		students.stream().filter(s -> s.getSex() == Gender.FEMALE).forEach(System.out::println);

		Map<Grade, Long> collect = students.stream().collect(Collectors.groupingBy(s -> s.getGrade(), Collectors.counting()));

		MapUtils.verbosePrint(System.out, "各班人数统计", collect);

		// IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toMap((i) -> i, (i)
		// -> i));

	}

}
