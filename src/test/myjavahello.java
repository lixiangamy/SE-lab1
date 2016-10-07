package test;
import java.io.*;

public class myjavahello {
	static String derivative(String str2,String x)//str2为表达式，x为变量
	{
		int sit,i,f=0,add_num,sub_num,num,mi,xishu,fine,count=0;
		String dan,news2="",resu="";//dan为开始的单项式，news2为新多项式
		if(x.equalsIgnoreCase(""))
		{
			System.out.println("variable is null");
			return str2;
		}
		for(i=0;i<x.length();i++)//非法情况
		{
			boolean b=('a'<=x.charAt(i)&&'z'>=x.charAt(i))||('A'<=x.charAt(i)&&'Z'>=x.charAt(i));
			if(b) f+=0;
			else f+=1;
		}
		if(f>0)//变量输入非法
		{
			System.out.println("variable is illegal");
			return str2;
		}
		sit=str2.indexOf(x);
		if(sit==-1)//没有变量
		{
			System.out.println("Error, no variable");
			return str2;
		}

		do
		{
			fine=0;
			count++;
			if(count>1)
			{
			add_num=str2.substring(1).indexOf('+');
			sub_num=str2.substring(1).indexOf('-');
			}
			else
			{
				add_num=str2.indexOf('+');
				sub_num=str2.indexOf('-');
				if(sub_num==0)
				{
					sub_num=str2.substring(1).indexOf('-');
					if(sub_num!=-1)
					sub_num++;
					fine=1;
				}
			}
			if(add_num<sub_num)
			{
				if(add_num!=-1)
				{
					num=add_num;
				}
				else
				{
					num=sub_num;
				}
			}
			else if(add_num>sub_num)
			{
				if(sub_num!=-1)
				{
					num=sub_num;
				}
				else
				{
					num=add_num;
				}
				
			}
			else
			{
				num=-1;
			}
			if(num==-1)
			{
				if(count!=1)
				dan=str2.substring(1);
				else if(fine!=1)
				dan=str2;
				else
				dan=str2.substring(1);
			}
			else
			{
				if(count==1&&fine!=1)
				dan=str2.substring(0,num);//开头的单项式
				else if(count==1)
				dan=str2.substring(1,num);
				else
				dan=str2.substring(1,num+1);
			}
			sit=dan.indexOf(x);
			//System.out.println(dan);
			if(sit==-1)
			{
				resu="";
			}
			else if(dan.equals(x))
			{
				resu="1";//则取变量前的所有内容为导数
			}
			else if(dan.substring(dan.length()-x.length()).equals(x))//若变量在单项式末尾
			{
				resu=dan.substring(0,dan.length()-x.length()-1);//则取变量前的所有内容为导数	
			}
			else if(!dan.substring(dan.length()-x.length()).equals(x))
			{
				if(dan.charAt(dan.indexOf(x)+x.length())!='^')
				{
					if(dan.indexOf(x)==0)//若x在单项式开头
					{
						
							resu=dan.substring(x.length()+1);
					}
					else
					{
							resu=dan.substring(0,dan.indexOf(x)-1)+dan.substring(dan.indexOf(x)+x.length());
					}
				}
				else
				{
					if(dan.charAt(0)>='0'&&dan.charAt(0)<='9')//开头有系数
					{
						if((dan.charAt(dan.indexOf(x)+x.length()+1)=='2')&&(dan.indexOf(x)+x.length()+1==dan.length()-1)||(dan.indexOf(x)+x.length()+1!=dan.length()-1 && dan.charAt(dan.indexOf(x)+x.length()+2)=='*'))//若x为二次幂
						{
							for(i=0;i<dan.length()&&dan.charAt(i)>='0'&&dan.charAt(i)<='9';i++);
							mi=Integer.parseInt(dan.substring(0,i));//系数
							resu=String.valueOf(mi*2)+dan.substring(i,dan.indexOf(x))+x+dan.substring(dan.indexOf(x)+x.length()+2);
					
						}
						else
						{
							for(i=dan.indexOf(x)+x.length()+1;i<dan.length()&&dan.charAt(i)!='*';i++);
							mi=Integer.parseInt(dan.substring(dan.indexOf(x)+x.length()+1,i));//幂次
							for(i=0;i<dan.length()&&dan.charAt(i)>='0'&&dan.charAt(i)<='9';i++);
							xishu=Integer.parseInt(dan.substring(0,i));//系数
							resu=String.valueOf(mi*xishu)+dan.substring(i,dan.indexOf(x))+x+'^'+String.valueOf(mi-1)+dan.substring(dan.indexOf(x)+x.length()+1+String.valueOf(mi-1).length());
							
						}
					}
					else//开头没系数
					{
						if((dan.charAt(dan.indexOf(x)+x.length()+1)=='2')&&(dan.indexOf(x)+x.length()+1==dan.length()-1)||(dan.indexOf(x)+x.length()+1!=dan.length()-1 && dan.charAt(dan.indexOf(x)+x.length()+2)=='*'))//若x为二次幂
						{
							resu='2'+'*'+dan.substring(0,dan.indexOf(x))+x+dan.substring(dan.indexOf(x)+x.length()+2);
							
						}
						else
						{
							for(i=dan.indexOf(x)+x.length()+1;i<dan.length()&&dan.charAt(i)!='*';i++);
							mi=Integer.parseInt(dan.substring(dan.indexOf(x)+x.length()+1,i));//幂次
							if(fine==1)
								news2=news2+'-';
							resu=String.valueOf(mi)+'*'+dan.substring(0,dan.indexOf(x))+x+'^'+String.valueOf(mi-1)+dan.substring(dan.indexOf(x)+x.length()+1+String.valueOf(mi-1).length());
							
						}
						
					}
					
					
				}
		}
		if(count==1)
		{
			if(fine==1)
			{
				resu=str2.charAt(0)+resu;
				if(num!=-1)
					str2=str2.substring(dan.length()+1);
			}
			else
			{
				str2=str2.substring(dan.length());
			}
		}
		else
		{
			resu=str2.charAt(0)+resu;
			if(num!=-1)
			str2=str2.substring(dan.length()+1);
		}
		if(!resu.equals("")&&!resu.equals("+")&&!resu.equals("-"))
		{
			news2=news2+resu;
		}
	}while(num!=-1);
		//System.out.println(news2);
		if(news2.charAt(0)=='+')
		{
			news2=news2.substring(1);
		}
		if(news2.charAt(news2.length()-1)=='+'||news2.charAt(news2.length()-1)=='-')
		{
			news2=news2.substring(0,news2.length()-1);
		}
		System.out.println(news2);
		return news2;
	}
	public static void simplify(String str2,String x)
	{
		int i,j,k,q,p,r,a,f=0,h=0,sit,add_num,m,sub_num,num,biao,d_sit,count=0,mi,fine=0;
		float xi,xi_n,nn;
		String x_n=x,xnn,rn,va,va_n,result,s_re="",dan="",vn_st,s_ren,s_rnd,re_n;//s_re是结果的和，result是单项式结果
		float vn;
		for(j=0;j<x_n.length();j++)
		{
			if(x_n.charAt(j)=='=')
			{
				va=x_n.substring(0,j);//变量
				f=0;
				for(i=0;i<va.length();i++)//非法情况
				{
					boolean b=('a'<=va.charAt(i)&&'z'>=va.charAt(i))||('A'<=va.charAt(i)&&'Z'>=va.charAt(i));
					if(b) f+=0;
					else f+=1;
				}
				if(f>0)//变量输入非法
				{
					System.out.println("variable is illegal");
				}
				sit=str2.indexOf(va);
				if(sit==-1)//没有变量
				{
					System.out.println("Error, no variable");
					return;
				}
				for(p=j;p<x_n.length()&&x_n.charAt(p)!=' ';p++);
				if(p!=x_n.length())
				{
					x_n=x_n.substring(p+1);
					j=-1;
				}
				else
				break;
			}
		}//end of for
		
		do
		{
			fine=0;
			count++;
			if(count>1)
			{
			add_num=str2.substring(1).indexOf('+');
			sub_num=str2.substring(1).indexOf('-');
			}
			else
			{
				add_num=str2.indexOf('+');
				sub_num=str2.indexOf('-');
				if(sub_num==0)
				{
					sub_num=str2.substring(1).indexOf('-');
					if(sub_num!=-1)
					sub_num++;
					fine=1;
				}
			}
			if(add_num<sub_num)
			{
				if(add_num!=-1)
				{
					num=add_num;
				}
				else
				{
					num=sub_num;
				}
			}
			else if(add_num>sub_num)
			{
				if(sub_num!=-1)
				{
					num=sub_num;
				}
				else
				{
					num=add_num;
				}
				
			}
			else
			{
				num=-1;
			}
			if(num==-1)
			{
				if(count!=1)
				dan=str2.substring(1);
				else if(fine!=1)
				dan=str2;
				else
				dan=str2.substring(1);
			}
			else
			{
				if(count==1&&fine!=1)
				dan=str2.substring(0,num);//开头的单项式
				else if(count==1)
				dan=str2.substring(1,num);
				else
				dan=str2.substring(1,num+1);
			}
			result=dan;
			xnn=x;
			for(j=0;j<xnn.length();j++)
			{
				if(xnn.charAt(j)=='=')
				{
					va=xnn.substring(0,j);//变量
					for(k=j+1;k<xnn.length();k++)
					{
						if(xnn.charAt(k)==' ')
						{
							break;
						}
					}
					vn_st=xnn.substring(j+1,k);
					vn=Float.parseFloat(xnn.substring(j+1,k));//变量的值
					d_sit=result.indexOf(va);
					if(d_sit==-1)
					{
						result=result;			
					}
					else if(result.equals(va))
					{
						result=vn_st;//则取变量前的所有内容为导数
					}
					else if(result.substring(result.length()-va.length()).equals(va))//若变量在单项式末尾
					{
						if(result.charAt(0)>='0'&&result.charAt(0)<='9'||result.charAt(0)=='-')
						{
							for(i=0;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(i)=='-');i++);
							xi=Float.parseFloat(result.substring(0,i));//系数
							result=String.valueOf(xi*vn)+result.substring(i,result.indexOf(va)-1);//则取变量前的所有内容为导数
						}
						else
						{
							if(vn==1)
							result=result.substring(0,result.indexOf(va)-1);
							else
							result=vn_st+'*'+result.substring(0,result.indexOf(va)-1);
						}
					}
					else if(!result.substring(result.length()-va.length()).equals(va))
					{
						if(result.charAt(result.indexOf(va)+va.length())!='^')//没有幂
						{
							if(result.indexOf(va)==0)//若x在单项式开头
							{
								if(vn==1)
								{
									result=result.substring(vn_st.length()+1);
								}
								else
								{
									result=vn_st+result.substring(vn_st.length());
								}
								
							}
							else
							{
								if(vn==1)
								{
									result=result.substring(0, result.indexOf(va))+result.substring(result.indexOf(va)+va.length()+1);
								}
								else
								{
									if(result.charAt(0)>='0'&&result.charAt(0)<='9'||result.charAt(0)=='-')
									{
										for(i=0;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(0)=='-');i++);
										xi=Float.parseFloat(result.substring(0,i));//系数
										result=String.valueOf(xi*vn)+result.substring(i,result.indexOf(va))+result.substring(result.indexOf(va)+va.length()+1);//则取变量前的所有内容为导数
									}
									else
									{
										result=vn_st+'*'+result.substring(0,result.indexOf(va))+result.substring(result.indexOf(va)+va.length()+1);
									}
								}
							}
						}//end of 没有幂
						else//有幂
						{
							if(result.charAt(0)>='0'&&result.charAt(0)<='9')//开头有系数
							{
								for(i=result.indexOf(va)+va.length()+1;i<result.length()&&result.charAt(i)!='*';i++);
								mi=Integer.parseInt(result.substring(result.indexOf(va)+va.length()+1,i));//幂次
								for(i=0;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.');i++);
								xi=Float.parseFloat(result.substring(0,i));//系数
								for(q=1;q<=mi;q++)
								{
									xi=xi*vn;
								}
								result=String.valueOf(xi)+result.substring(i,result.indexOf(va)-1)+result.substring(result.indexOf(va)+va.length()+1+String.valueOf(mi).length());
							}
							else//开头没系数
							{
								for(i=result.indexOf(va)+va.length()+1;i<result.length()&&result.charAt(i)!='*';i++);
								mi=Integer.parseInt(result.substring(result.indexOf(va)+va.length()+1,i));//幂次
								xi=1;
								for(q=1;q<=mi;q++)
								{
									xi=xi*vn;
								}
								if(result.indexOf(va)!=0)
								result=String.valueOf(xi)+'*'+result.substring(0,result.indexOf(va)-1)+result.substring(result.indexOf(va)+va.length()+1+String.valueOf(mi).length());
								else
								result=String.valueOf(xi)+result.substring(result.indexOf(va)+va.length()+1+String.valueOf(mi).length());
							}	
						}//end of 有幂
					}//end of 不在结尾
					for(p=j;p<xnn.length()&&xnn.charAt(p)!=' ';p++);
					if(p!=xnn.length())
					{
						xnn=xnn.substring(p+1);
						j=-1;
					}
					else
					break;
				}
			}
			
			if(count==1)
			{
				if(fine==1)
				{
					result=str2.charAt(0)+result;
					if(num!=-1)
						str2=str2.substring(dan.length()+1);
				}
				else
				{
					str2=str2.substring(dan.length());
					if(result.charAt(0)!='-')
					result='+'+result;
				}
			}
			else
			{
				result=str2.charAt(0)+result;
				if(num!=-1)
				str2=str2.substring(dan.length()+1);
			}
			if(result.length()>=2)
			{
				if(result.substring(0,2)=="--")
					result='+'+result.substring(2);
				else if(result.substring(0,2)=="+-")
					result=result.substring(1);
			}
			if(s_re.equals(""))
			{
				s_re=s_re+" "+result;
			}
			else
			{
				re_n=result;
				s_ren=s_re;
				for(q=1;q<=s_ren.length();q++)
				{
					if((q<s_ren.length()&&s_ren.charAt(q)==' ')||(q==s_ren.length()))
					{
						s_rnd=s_ren.substring(1, q);//单项式
						h=0;
						rn=s_rnd;
						for(p=0;p<rn.length();p++)
						{
							if(rn.charAt(p)>='a'&&rn.charAt(p)<='z'||rn.charAt(p)>='A'&&rn.charAt(p)<='Z')
							{
								for(r=p;r<rn.length()&&rn.charAt(r)!='*';r++);
								va_n=rn.substring(p, r);//变量
								if(re_n.indexOf(va_n)==-1)
								{
									h=1;
									break;
								}
								else
								{
									if(va_n.indexOf('^')!=-1)
									{
										rn=rn.substring(r);
										p=-1;
										re_n=re_n.substring(0, re_n.indexOf(va_n))+re_n.substring(re_n.indexOf(va_n)+va_n.length());
									}
									else
									{
										if((re_n.indexOf(va_n)+va_n.length()>=re_n.length())||(re_n.indexOf(va_n)+va_n.length()<re_n.length()&&re_n.charAt(re_n.indexOf(va_n)+va_n.length())!='^'))
										{
											rn=rn.substring(r);
											p=-1;
											re_n=re_n.substring(0, re_n.indexOf(va_n))+re_n.substring(re_n.indexOf(va_n)+va_n.length());
										}
										else
										{
											h=1;
											break;
										}
									}
								}
							}
						}
						if(h==0)
						{
							for(a=0;a<re_n.length();a++)
							{
								if(re_n.charAt(a)>='a'&&re_n.charAt(a)<='z'||re_n.charAt(a)>='A'&&re_n.charAt(a)<='Z')
								{
									h=1;
								}
							}
						}
						if(h==0)
						{
							if(result.charAt(0)=='-')
							{
								if(result.charAt(1)=='-')
								{
									for(i=0;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(i)=='-');i++);
									xi=Float.parseFloat(result.substring(2,i));//系数
								}
								else
								{
									for(i=0;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(i)=='-');i++);
									xi=Float.parseFloat(result.substring(0,i));//系数
								}
							}
							else
							{
								if(result.charAt(1)=='-')
								{
									for(i=1;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(i)=='-');i++);
									xi=Float.parseFloat(result.substring(1,i));//系数
								}
								else
								{
									for(i=1;i<result.length()&&(result.charAt(i)>='0'&&result.charAt(i)<='9'||result.charAt(i)=='.'||result.charAt(i)=='-');i++);
									xi=Float.parseFloat(result.substring(1,i));//系数
								}
							}
							if(s_rnd.charAt(0)=='-')
							{
								if(s_rnd.charAt(1)=='-')
								{
									for(i=0;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9'||s_rnd.charAt(i)=='.'||s_rnd.charAt(i)=='-');i++);
									xi_n=Float.parseFloat(s_rnd.substring(2,i));//系数
								}
								else
								{
									for(i=0;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9'||s_rnd.charAt(i)=='.'||s_rnd.charAt(i)=='-');i++);
									xi_n=Float.parseFloat(s_rnd.substring(0,i));//系数
								}
							}
							else
							{
								if(s_rnd.charAt(1)=='-')
								{
								for(i=1;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9'||s_rnd.charAt(i)=='.'||s_rnd.charAt(i)=='-');i++);
								xi_n=Float.parseFloat(s_rnd.substring(1,i));//系数
								}
								else
								{
									for(i=1;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9'||s_rnd.charAt(i)=='.'||s_rnd.charAt(i)=='-');i++);
									xi_n=Float.parseFloat(s_rnd.substring(1,i));//系数
								}
							}
							nn=xi+xi_n;
							if(nn>=0&&s_rnd.charAt(0)=='-')
							s_re=s_re.substring(0,s_re.indexOf(s_rnd))+'+'+String.valueOf(xi+xi_n)+s_re.substring(s_re.indexOf(s_rnd)+i);
							else if(nn<0&&s_rnd.charAt(0)=='-')
							s_re=s_re.substring(0,s_re.indexOf(s_rnd))+String.valueOf(xi+xi_n)+s_re.substring(s_re.indexOf(s_rnd)+i);
							else if(nn>=0&&s_rnd.charAt(0)=='+')
							s_re=s_re.substring(0,s_re.indexOf(s_rnd)+1)+String.valueOf(xi+xi_n)+s_re.substring(s_re.indexOf(s_rnd)+i);
							else
							s_re=s_re.substring(0,s_re.indexOf(s_rnd))+String.valueOf(xi+xi_n)+s_re.substring(s_re.indexOf(s_rnd)+i);
							break;
						}
					s_ren=s_ren.substring(q);
					q=0;
					}
				}
				if(h==1)
				{
					s_re=s_re+" "+result;
				}
			}
		}while(num!=-1);
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)==' ')
			{
				s_re=s_re.substring(0,i)+s_re.substring(i+1);
			}
		}
		if(s_re.charAt(0)=='+')
		{
			s_re=s_re.substring(1);
		}
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)=='+'&&s_re.charAt(i+1)=='-')
			{
				s_re=s_re.substring(0,i)+s_re.substring(i+1);
			}
			else if(s_re.charAt(i)=='-'&&s_re.charAt(i+1)=='-')
			{
				s_re=s_re.substring(0,i)+'+'+s_re.substring(i+2);
			}
		}
		biao=0;
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)>='a'&&s_re.charAt(i)<='z'||s_re.charAt(i)>='A'&&s_re.charAt(i)<='Z')
				biao=1;
		}
		if(biao==1||Float.parseFloat(s_re)!=0)
		{	
			for(i=0;i<s_re.length();i++)
			{
				if(s_re.charAt(i)=='0')
				{
					if(i==0||(s_re.charAt(i-1)!='.'&&!(s_re.charAt(i-1)>='0'&&s_re.charAt(i-1)<='9')))
					{
						for(m=i;m<s_re.length()&&(s_re.charAt(m)>='0'&&s_re.charAt(m)<='9'||s_re.charAt(m)=='.');m++);
						if(Float.parseFloat(s_re.substring(i,m))==0)
						{
							add_num=s_re.substring(i).indexOf('+');
							sub_num=s_re.substring(i).indexOf('-');
							if(add_num<sub_num)
							{
								if(add_num!=-1)
								{
									num=add_num;
								}
								else
								{
									num=sub_num;
								}
								
							}
							else if(add_num>sub_num)
							{
								if(sub_num!=-1)
								{
									num=sub_num;
								}
								else
								{
									num=add_num;
								}
								
							}
							else
							{
								num=-1;
							}
							if(num==-1)
							{
								s_re=s_re.substring(0,i-1);
							}
							else
							{
								if(i!=0)
								s_re=s_re.substring(0,i-1)+s_re.substring(num);
								else
								{
									if(s_re.charAt(num)=='+')
										s_re=s_re.substring(num+1);
									else
										s_re=s_re.substring(num);
								}
							}
						}
					}
				}
			}
			System.out.println(s_re);
		}
		else
		{
			System.out.println("0");
		}
}
public static String expression(String str1,String str2)
{
		String s2,z,sh,dan,res,var,dans,cun,dant="",s_rnd,rn,va_n,re_n,st4="",s_re="",str3="";
		int i,j,h=0,add_num,r,a,sub_num,num,q,xi=1,n=0,begin,kk=0,xiabiao,sit,mn=0,count,nn,p=0,m=0,biao,pq=0,ch=0,xis,xi_n;
		for(i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)==' '||str1.charAt(i)=='\t')
			{
				if(i==0)
				{
					str1=str1.substring(1);
					i=-1;
				}
				else if(i==str1.length()-1)
				{
					str1=str1.substring(0,i);
				}
				else
				{
					str1=str1.substring(0,i)+str1.substring(i+1);
					i--;
				}
			}
		}
		if(str1.equals(""))
		{
			System.out.println("polynomial is illegal");
			return str2;
		}
		for(i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)!='*'&&str1.charAt(i)!='+'&&str1.charAt(i)!='-'&&str1.charAt(i)!='^'&&!(str1.charAt(i)>='0'&&str1.charAt(i)<='9')&&!(str1.charAt(i)>='a'&&str1.charAt(i)<='z'||str1.charAt(i)>='A'&&str1.charAt(i)<='Z'))
			{
				System.out.println("polynomial is illegal");
				return str2;
			}
		}
		
		for(i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)=='*'||str1.charAt(i)=='+'||str1.charAt(i)=='-'||str1.charAt(i)=='^')
			{
				if(i==str1.length()-1||i==0)
				{
					System.out.println("polynomial is illegal");
					return str2;
				}
				if(str1.charAt(i+1)=='*'||str1.charAt(i+1)=='+'||str1.charAt(i+1)=='-'||str1.charAt(i+1)=='^')
				{
					System.out.println("polynomial is illegal");
					return str2;
				}
			}
		}
		for(i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)=='^')
			{
				if(i==0||i==str1.length()-1||!(str1.charAt(i-1)>='a'&&str1.charAt(i-1)<='z'||str1.charAt(i-1)>='A'&&str1.charAt(i-1)<='Z')||!(str1.charAt(i+1)>='0'&&str1.charAt(i+1)<='9'))
				{
					System.out.println("polynomial is illegal");
					return str2;
				}
			}
		}
		sh=str1;
		cun=" ";
		for(i=0;i<sh.length();i++)
		{
			if(sh.charAt(i)>='a'&&sh.charAt(i)<='z'||sh.charAt(i)>='A'&&sh.charAt(i)<='Z')
			{
				for(j=i;j<sh.length()&&(sh.charAt(j)>='a'&&sh.charAt(j)<='z'||sh.charAt(j)>='A'&&sh.charAt(j)<='Z');j++);
				z=sh.substring(i,j);
				if(cun.indexOf(z)!=-1)
				{
					System.out.println("variable is illegal");
					return str2;
				}
				cun=cun+" "+z;
				//System.out.println(z);
				if(j<sh.length())
				sh=sh.substring(0,i)+sh.substring(j+1);
				else
				sh=sh.substring(0,i);
				while(sh.indexOf(z)!=-1)
				{
					if(sh.indexOf(z)+z.length()==sh.length()||(!(sh.charAt(sh.indexOf(z)+z.length())>='a'&&sh.charAt(sh.indexOf(z)+z.length())<='z'||sh.charAt(sh.indexOf(z)+z.length())>='A'&&sh.charAt(sh.indexOf(z)+z.length())<='Z')))
					{
						if(sh.indexOf(z)+z.length()==sh.length())
						{
							sh=sh.substring(0,sh.indexOf(z));
						}
						else
						{
							sh=sh.substring(0,sh.indexOf(z))+sh.substring(sh.indexOf(z)+z.length());
						}
						
					}
					else
					{
						System.out.println("variable is illegal");
						return str2;
					}
				}
				i=-1;
			}
		}
		for(i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)>='0'&&str1.charAt(i)<='9'&&i+1<str1.length()&&(str1.charAt(i+1)>='a'&&str1.charAt(i+1)<='z'||str1.charAt(i+1)>='A'&&str1.charAt(i+1)<='Z'))
			{
				str1=str1.substring(0,i+1)+'*'+str1.substring(i+1);
			}
			if(i+1<str1.length()&&str1.charAt(i+1)>='0'&&str1.charAt(i+1)<='9'&&(str1.charAt(i)>='a'&&str1.charAt(i)<='z'||str1.charAt(i)>='A'&&str1.charAt(i)<='Z'))
			{
				str1=str1.substring(0,i+1)+'*'+str1.substring(i+1);
			}
		}
		s2=str1;
		do
		{
			add_num=s2.indexOf('+');
			sub_num=s2.indexOf('-');
			
			if(add_num<sub_num)
			{
				if(add_num!=-1)
				{
					num=add_num;
				}
				else
				{
					num=sub_num;
				}
			}
			else if(add_num>sub_num)
			{
				if(sub_num!=-1)
				{
					num=sub_num;
				}
				else
				{
					num=add_num;
				}
			}
			else
			{
				num=-1;
			}
			if(num!=-1)
			{
				dan=s2.substring(0,num);
			}
			else
			{
				dan=s2;
			}
			begin=str1.indexOf(dan);
			ch=dan.length();
			xi=1;
			xiabiao=0;
			for(i=0;i<dan.length();i++)
			{
				if(dan.charAt(i)>='0'&&dan.charAt(i)<='9')
				{
					for(n=i;n>=0&&dan.charAt(n)>='0'&&dan.charAt(n)<='9';n--);
					if(n==-1||dan.charAt(n)!='^')
					{
						for(j=i;j<dan.length()&&dan.charAt(j)>='0'&&dan.charAt(j)<='9';j++);
						xi=xi*Integer.parseInt(dan.substring(i,j));
						if(j!=dan.length())
						dan=dan.substring(0,i)+dan.substring(j+1);
						else
						{
							if(i==0)
							dan="";
							else
							dan=dan.substring(0,i-1);
						}
						//System.out.println(xi);
						//System.out.println("j="+j);
						xiabiao+=2;
						i=-1;
					}
				}
			}
			if(dan!="")
			dan=String.valueOf(xi)+'*'+dan;
			else
			dan=String.valueOf(xi);
			dans=dan;
			//System.out.println(dan);
			for(i=0;i<dan.length();i++)//合并字母
			{
				//System.out.println("***");
				//System.out.println(i);
				if(dan.charAt(i)>='a'&&dan.charAt(i)<='z'||dan.charAt(i)>='A'&&dan.charAt(i)<='Z')
				{
					//System.out.println(i);
					for(j=i;(j<dan.length()&&(dan.charAt(j)>='a'&&dan.charAt(j)<='z'||dan.charAt(j)>='A'&&dan.charAt(j)<='Z'));j++);
					//System.out.println(j);
					var=dan.substring(i,j);
					//System.out.println("var="+var);
					if(j==dan.length()||dan.charAt(j)!='^')
					count=1;
					else
					{
						for(p=j+1;p<dan.length()&&dan.charAt(p)>='0'&&dan.charAt(p)<='9';p++);
						count=Integer.parseInt(dan.substring(j+1,p));
					}
					dans=dan.substring(j);
					//System.out.println("count="+count);
					do{
						sit=dans.indexOf(var);
						if(sit!=-1)
						{
							if(sit+var.length()==dans.length())
							{
								count++;
							}
							else if(dans.charAt(sit+var.length())!='^')
							{
								count++;
							}
							else
							{
								for(q=sit+var.length()+1;q<dans.length()&&dans.charAt(q)>='0'&&dans.charAt(q)<='9';q++);
								count+=Integer.parseInt(dans.substring(sit+var.length()+1,q));
							}
							if(sit+var.length()!=dans.length())
							dans=dans.substring(sit+var.length());
							else
							dans="";
						}
					}while(sit!=-1);
					if(count>1)
					dant=dan.substring(0,j)+'^'+String.valueOf(count);
					else
					dant=dan.substring(0,j);
					//System.out.println("dant="+dant);
					//System.out.println("count="+count);
					//System.out.println("dan="+dan);
					pq=dant.length();
					if(j==dan.length())
					dan="";
					else if(dan.charAt(j)!='^')
					dan=dan.substring(j);
					else if(p==dan.length())
					dan="";
					else
					dan=dan.substring(p);
					do
					{
						sit=dan.indexOf(var);
						if(sit!=-1)
						{
							if(sit+var.length()==dan.length())
							{
								dant=dant+dan.substring(0,sit-1);
								dan="";
							}
							else if(dan.charAt(sit+var.length())!='^')
							{
								dant=dant+dan.substring(0,sit-1);
								dan=dan.substring(sit+var.length());
							}
							else
							{
								for(q=sit+var.length()+1;q<dan.length()&&dan.charAt(q)>='0'&&dan.charAt(q)<='9';q++);
								if(q==dan.length())
								{
									dant=dant+dan.substring(0,sit-1);
									dan="";
								}
								else
								{
									dant=dant+dan.substring(0,sit-1);
									dan=dan.substring(q);
								}
							}
						}
						else
						{
							dant=dant+dan;
						}
					}while(sit!=-1);
					dan=dant;
					i=pq;
				}
				//System.out.println("dan="+dan);
			}
			//System.out.println(str1);
			if(begin+ch<str1.length())
			str1=str1.substring(0,begin)+dan+str1.substring(begin+ch);
			else
			str1=str1.substring(0,begin)+dan;
			//System.out.println(str1);
			if(num!=-1)
			{
				s2=s2.substring(num+1);
			}
		}while(num!=-1);
		//System.out.println(str1);
		add_num=str1.indexOf('+');
		sub_num=str1.indexOf('-');
		if(add_num<sub_num)
		{
			if(add_num!=-1)
			{
				num=add_num;
			}
			else
			{
				num=sub_num;
			}
		}
		else if(add_num>sub_num)
		{
			if(sub_num!=-1)
			{
				num=sub_num;
			}
			else
			{
				num=add_num;
			}
		}
		else
		{
			num=-1;
		}
		if(num!=-1)
			st4=" +"+str1.substring(0,num);
		else
		{	
			s_re=str1;
			kk=1;
		}
		//System.out.println(num);
		if(kk==0)
		{
			s2=str1.substring(num);
			num=-1;
			do{
				add_num=s2.substring(1).indexOf('+');
				sub_num=s2.substring(1).indexOf('-');
				
				if(add_num<sub_num)
				{
					if(add_num!=-1)
					{
						num=add_num;
					}
					else
					{
						num=sub_num;
					}
				}
				else if(add_num>sub_num)
				{
					if(sub_num!=-1)
					{
						num=sub_num;
					}
					else
					{
						num=add_num;
					}
				}
				else
				{
					num=-1;
				}
				//System.out.println(s2);
				//System.out.println(num);
				//System.out.println("ss");
				if(num!=-1)
				{
					res=s2.substring(0,num+1);
					s2=s2.substring(num+1);
				}
				else
				{
					res=s2;
				}
				re_n=res;
				str3=st4;
				//System.out.println(res);
				for(q=1;q<=str3.length();q++)
				{
					if((q<str3.length()&&str3.charAt(q)==' ')||(q==str3.length()))
					{
						s_rnd=str3.substring(1, q);//单项式
						h=0;
						rn=s_rnd;
						for(p=0;p<rn.length();p++)
						{
							if(rn.charAt(p)>='a'&&rn.charAt(p)<='z'||rn.charAt(p)>='A'&&rn.charAt(p)<='Z')
							{
								for(r=p;r<rn.length()&&rn.charAt(r)!='*';r++);
								va_n=rn.substring(p, r);//变量
								if(re_n.indexOf(va_n)==-1)
								{
									h=1;
									break;
								}
								else
								{
									if(va_n.indexOf('^')!=-1)
									{
										rn=rn.substring(r);
										p=-1;
										re_n=re_n.substring(0, re_n.indexOf(va_n))+re_n.substring(re_n.indexOf(va_n)+va_n.length());
									}
									else
									{
										if((re_n.indexOf(va_n)+va_n.length()>=re_n.length())||(re_n.indexOf(va_n)+va_n.length()<re_n.length()&&re_n.charAt(re_n.indexOf(va_n)+va_n.length())!='^'))
										{
											rn=rn.substring(r);
											p=-1;
											re_n=re_n.substring(0, re_n.indexOf(va_n))+re_n.substring(re_n.indexOf(va_n)+va_n.length());
										}
										else
										{
											h=1;
											break;
										}
									}
								}
							}
						}
						if(h==0)
						{
							for(a=0;a<re_n.length();a++)
							{
								if(re_n.charAt(a)>='a'&&re_n.charAt(a)<='z'||re_n.charAt(a)>='A'&&re_n.charAt(a)<='Z')
								{
									h=1;
								}
							}
						}
						if(h==0)
						{
							if(res.charAt(0)=='-')
							{
									for(i=0;i<res.length()&&(res.charAt(i)>='0'&&res.charAt(i)<='9'||res.charAt(i)=='-');i++);
									xis=Integer.parseInt(res.substring(0,i));//系数
							}
							else
							{
									for(i=1;i<res.length()&&(res.charAt(i)>='0'&&res.charAt(i)<='9');i++);
									xis=Integer.parseInt(res.substring(1,i));//系数
							}
							if(s_rnd.charAt(0)=='-')
							{
									for(i=0;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9'||s_rnd.charAt(i)=='-');i++);
									xi_n=Integer.parseInt(s_rnd.substring(0,i));//系数
							
							}
							else
							{
									for(i=1;i<s_rnd.length()&&(s_rnd.charAt(i)>='0'&&s_rnd.charAt(i)<='9');i++);
									xi_n=Integer.parseInt(s_rnd.substring(1,i));//系数
							
							}
							nn=xis+xi_n;
							if(nn>=0&&s_rnd.charAt(0)=='-')
							st4=st4.substring(0,st4.indexOf(s_rnd))+'+'+String.valueOf(xis+xi_n)+st4.substring(st4.indexOf(s_rnd)+i);
							else if(nn<0&&s_rnd.charAt(0)=='-')
							st4=st4.substring(0,st4.indexOf(s_rnd))+String.valueOf(xis+xi_n)+st4.substring(st4.indexOf(s_rnd)+i);
							else if(nn>=0&&s_rnd.charAt(0)=='+')
							st4=st4.substring(0,st4.indexOf(s_rnd)+1)+String.valueOf(xis+xi_n)+st4.substring(st4.indexOf(s_rnd)+i);
							else
							st4=st4.substring(0,st4.indexOf(s_rnd))+String.valueOf(xis+xi_n)+st4.substring(st4.indexOf(s_rnd)+i);
							break;
						}
					str3=str3.substring(q);
					q=0;
					}
				}
				if(h==1)
				{
					//System.out.println("res="+res);
					st4=st4+" "+res;
					//System.out.println("st4="+st4);
				}
			}while(num!=-1);
			s_re=st4;
		}
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)==' ')
			{
				s_re=s_re.substring(0,i)+s_re.substring(i+1);
			}
		}
		if(s_re.charAt(0)=='+')
		{
			s_re=s_re.substring(1);
		}
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)=='+'&&s_re.charAt(i+1)=='-')
			{
				s_re=s_re.substring(0,i)+s_re.substring(i+1);
			}
			else if(s_re.charAt(i)=='-'&&s_re.charAt(i+1)=='-')
			{
				s_re=s_re.substring(0,i)+'+'+s_re.substring(i+2);
			}
		}
		biao=0;
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)>='a'&&s_re.charAt(i)<='z'||s_re.charAt(i)>='A'&&s_re.charAt(i)<='Z')
				biao=1;
		}
		for(i=0;i<s_re.length();i++)
		{
			if(s_re.charAt(i)=='1')
			{
				if((i==0||!(s_re.charAt(i-1)>='0'&&s_re.charAt(i-1)<='9'))&&i+1<s_re.length()&&s_re.charAt(i+1)=='*')
				{
					s_re=s_re.substring(0,i)+s_re.substring(i+2);
					
				}
			}
		}
		if(!s_re.equals(""))
			mn++;
		if(biao==1||Integer.parseInt(s_re)!=0)
		{	
			for(i=0;i<s_re.length();i++)
			{
				if(s_re.charAt(i)=='0')
				{	
					if(i==0||!(s_re.charAt(i-1)>='0'&&s_re.charAt(i-1)<='9'))
					{
						add_num=s_re.substring(i).indexOf('+');
						sub_num=s_re.substring(i).indexOf('-');
						if(add_num<sub_num)
						{
							if(add_num!=-1)
							{
								num=add_num;
							}
							else
							{
								num=sub_num;
							}
							
						}
						else if(add_num>sub_num)
						{
							if(sub_num!=-1)
							{
								num=sub_num;
							}
							else
							{
								num=add_num;
							}
							
						}
						else
						{
							num=-1;
						}
						if(num==-1)
						{
							if(i-1<0)
								s_re="";
							else
							s_re=s_re.substring(0,i-1);
						}
						else
						{
							if(i!=0)
							s_re=s_re.substring(0,i-1)+s_re.substring(num);
							else
							{
								if(s_re.charAt(num)=='+')
									s_re=s_re.substring(num+1);
								else
									s_re=s_re.substring(num);
							}
						}
					}
				}
			}
			
		}
		else
		{
			s_re="0";
		}
		if(s_re.equals(""))
			s_re="0";
		System.out.println(s_re);
		return s_re;
}
	public static  void main(String args[])throws IOException
	{
		String str1="";
		String str2="";
		do{
		System.out.print(">");
		BufferedReader buf;
		buf=new BufferedReader(new InputStreamReader(System.in));
		str1=buf.readLine();
		if(!str1.equalsIgnoreCase("end"))
		{
			if(str1.length()>=5&&str1.substring(0,5).equalsIgnoreCase("!d/d "))
			{
				if(str2.equalsIgnoreCase(""))
				{
					System.out.println("错误：未输入合法多项式！");
				}
				else
				{
					str2=derivative(str2,str1.substring(5));
				}
			}
			else if(str1.length()>=10&&str1.substring(0,10).equalsIgnoreCase("!simplify "))
			{
				if(str2.equalsIgnoreCase(""))
				{
					System.out.println("错误：未输入合法多项式！");
				}
				else if(str1.length()==10&&str1.equalsIgnoreCase("!simplify "))
				{
					System.out.println(str2);
				}
				else
				{
					simplify(str2,str1.substring(10));
				}
			}
			else
			{
				str2=expression(str1,str2);//前缀性变量报错
			}
		}
		}while(!"end".equalsIgnoreCase(str1));
	}
}
