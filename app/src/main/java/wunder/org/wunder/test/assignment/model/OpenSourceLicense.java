package wunder.org.wunder.test.assignment.model;

/**
 * Created by medchiheb on 11/04/17.
 */

public class OpenSourceLicense
{
    private String mLicenseName;
    private String mLicenseUrl;

    public OpenSourceLicense(String paramString1, String paramString2)
    {
        this.mLicenseName = paramString1;
        this.mLicenseUrl = paramString2;
    }



    public String getLicenseName()
    {
        return this.mLicenseName;
    }

    public String getLicenseUrl()
    {
        return this.mLicenseUrl;
    }


}