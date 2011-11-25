package ece.vt.edu.model

public class AcidBond
{
	LatticeBead a;
	LatticeBead b;
	
	public AcidBond(LatticeBead _a, LatticeBead _b)
	{
		a=_a;
		b=_b;
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
}