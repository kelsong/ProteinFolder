package ece.vt.edu.model;

public class AcidBond implements Comparable<AcidBond>
{
	LatticeBead a;
	LatticeBead b;
	
	public AcidBond(LatticeBead _a, LatticeBead _b)
	{
		a=_a;
		b=_b;
		
		//System.out.println("Creating bond: "+a.getAcid() + " "+a.getAcid());
	}
	
	public String toString()
	{
		return "("+a+","+b+")";
	}

	@Override
	public int compareTo(AcidBond that) {
		if(this.a==that.a && this.b==that.b)
		{
			return 0;
		}
		else if(this.a==that.b && this.b==that.a)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public boolean equals(Object that)
	{
		if(compareTo((AcidBond)that)==1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public int hashCode()
	{
		return a.hashCode()+b.hashCode();
	}
}