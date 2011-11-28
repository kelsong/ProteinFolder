package ece.vt.edu.model;

public class AcidBond implements Comparable<AcidBond>
{
	LatticeBead a;
	LatticeBead b;
	
	public AcidBond(LatticeBead _a, LatticeBead _b)
	{
		a=_a;
		b=_b;
		
		System.out.println("Creating bond: "+a.toString() + " "+b.toString());
	}

	public boolean equals(AcidBond that)
	{
		if(this.a==that.a && this.b==that.b)
		{
			return true;
		}
		else if(this.a==that.b && this.b==that.a)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int compareTo(AcidBond that) {
		if(this.a==that.a && this.b==that.b)
		{
			return 1;
		}
		else if(this.a==that.b && this.b==that.a)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}