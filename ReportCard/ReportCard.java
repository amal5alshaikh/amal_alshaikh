

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Amal Alshaikh
 *
 */
public class ReportCard {
	private int reportcardId;
	private String name;
	private int year;
	private double Average;
	private int sumgrades;
	public String[] Gradename = new String[6];
	private ArrayList<Integer> Grades = new ArrayList<Integer>();
	private ArrayList<String> subjects = new ArrayList<String>();

	public ReportCard(int studentreportcardId) {
		this.reportcardId = studentreportcardId;

	}

	{

		// adding element to subject Array list
		subjects.add(0, "Mathematics");
		subjects.add(1, "Science");
		subjects.add(2, "English");
		subjects.add(3, "Computer Science");
		subjects.add(4, "Poetry");
		subjects.add(5, "Drawing");

		// adding element to grade name array

	}

	public void getGrades() {
		{
			for (int e = 0; Gradename.length > e; e++)
				if (Gradename[e] == "A")
					Grades.add(100);
				else if (Gradename[e] == "B")
					Grades.add(80);
				else if (Gradename[e] == "C")
					Grades.add(70);
				else if (Gradename[e] == "D")
					Grades.add(60);
				else if (Gradename[e] == "F")
					Grades.add(50);
		}
	}

	public double getAverage() {

		for (int r = 0; Grades.size() > r; r++)
			sumgrades += Grades.get(r);
		Average = (sumgrades * 100) / 600;
		// a = Double.toString(Average);
		return (Average);

	}

	public int setreportcardId(int studentreportcardId) {
		reportcardId = studentreportcardId;
		return reportcardId;
	}

	public int setReportCardYear(int reportCardYear) {
		year = reportCardYear;
		return reportCardYear;
	}

	public String setStudentName(String StudintName) {
		name = StudintName;
		return StudintName;

	}

	@Override
	public String toString() {
		return "ReportCard [ Report Card Id= " + reportcardId + ", \nReport Card Year=" + year + ", \nStudinName="
				+ name + ",  \nAverage=+" + Average + "%" + ", \nsumgrades=" + sumgrades + ", \nGradename="
				+ Arrays.toString(Gradename) + ", \nGrades=" + Grades

				+ ", \nsubjects=" + subjects + "]";
	}

}
