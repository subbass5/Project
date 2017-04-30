package com.example.windowsnt.project;


public class CalculatorUnit {
    private float unitInput;
    private float mixPower;
    private float ft;
    private float service;
    private float totalPrice;
    private float getFT = -0.3729f;
    private float Service ;
    private float Service150f;
    private float FT_ = getFT;
    private float Power;
    private float Base ;
    private float FT ;
    private float VAT ;
    private float Summary ;
    private float Service150;
    private float To150_ = 0.00f;
    private float To400_ = 0.00f;
    private float More400_ = 0.00f;
    private float Rate150_ = 3.2484f;
    private float Rate400_ = 4.2218f;
    private float RateMore400_ = 4.4217f;
    private float To15 = 0.00f;
    private float To25 = 0.00f;
    private float To35 = 0.00f;
    private float To100 = 0.00f;
    private float To150 = 0.00f;
    private float To400 = 0.00f;
    private float More400 = 0.00f;
    private float Rate15 = 2.3488f;
    private float Rate25 = 2.9882f;
    private float Rate35 = 3.2405f;
    private float Rate100 = 3.6237f;
    private float Rate150 = 3.7171f;
    private float Rate400 = 4.2218f;
    private float RateMore400 = 4.4217f;
    private float Power150;
    private float Base150;
    private float VAT150;
    private float Summary150;

    public void Calculator(float Unit){
    //=ROUND(IF($C$4<6,0,IF($C$4<16,(C$4-0)*C8,(15*C8))),2)
    if (Unit < 6) {
        To15 = 0;
    } else if (Unit < 16) {
        To15 = Unit * Rate15;
    } else {
        To15 = 15 * Rate15;
    }
    //=ROUND(IF($C$4<16,0,IF($C$4<26,(C$4-15)*C9,(10*C9))),2)
    if (Unit < 16) {
        To25 = 0;
    } else if (Unit < 26) {
        To25 = (Unit - 15) * Rate25;
    } else {
        To25 = 10 * Rate25;
    }
    //=ROUND(IF($C$4<26,0,IF($C$4<36,(C$4-25)*C10,(10*C10))),2)
    if (Unit < 26) {
        To35 = 0;
    } else if (Unit < 36) {
        To35 = (Unit - 25) * Rate35;
    } else {
        To35 = 10 * Rate35;
    }
    //=ROUND(IF($C$4<36,0,IF($C$4<101,(C$4-35)*C11,(65*C11))),2)
    if (Unit < 36) {
        To100 = 0;
    } else if (Unit < 101) {
        To100 = (Unit - 35) * Rate100;
    } else {
        To100 = 65 * Rate100;
    }
    //=ROUND(IF($C$4<101,0,IF($C$4<151,(C$4-100)*C12,(50*C12))),2)
    if (Unit < 101) {
        To150 = 0;
    } else if (Unit < 151) {
        To150 = (Unit - 100) * Rate150;
    } else {
        To150 = 50 * Rate150;
    }
    //=ROUND(IF($C$4<151,0,IF($C$4<401,(C$4-150)*C13,(250*C13))),2)
    if (Unit < 151) {
        To400 = 0;
    } else if (Unit < 401) {
        To400 = (Unit - 150) * Rate400;
    } else {
        To400 = 250 * Rate400;
    }
    //=ROUND(IF($C$4<401,0,($C$4-400)*C14),2)
    if (Unit < 401) {
        More400 = 0;
    } else {
        More400 = (Unit - 400) * RateMore400;
    }

    //-------------- Case: Rate > 150 --------------

    //=ROUND(IF($C$4<151,$C$4*C8, 150*C8),2)
    if (Unit < 151) {
        To150_ = Unit * Rate150_;
    } else {
        To150_ = 150 * Rate150_;
    }
    //=ROUND(IF($C$4<151,0,IF($C$4<401,(C$4-150)*C9,(250*C9))),2)
    if (Unit < 151) {
        To400_ = 0;
    } else if (Unit < 401) {
        To400_ = (Unit - 150) * Rate400_;
    } else {
        To400_ = 250 * Rate400_;
    }
    //=ROUND(IF($C$4<401,0,($C$4-400)*C10),2)
    if (Unit < 401) {
        More400_ = 0;
    } else {
        More400_ = (Unit - 400) * RateMore400_;
    }

    //-------------- Calculate & Display Result --------------
        setService(8.19f);
        setService150(38.22f);
        FT_ = getFT;
        setPower(Round(To15) + Round(To25) + Round(To35) + Round(To100) + Round(To150) + Round(To400) + Round(More400));
        setBase(Round(getPower() + getService()));
        FT = Round(Unit * (-0.3729f)); //=ROUND(C4*(C5/100),2)
        VAT = Round((Base + FT) * 0.07f); //ROUND((D17+D19)*0.07,2)
        setSummary(getBase() + FT + VAT);

         setPower150(Round(To150_) + Round(To400_) + Round(More400_));
         setBase150(Round(getPower150() + Service150));
         setVAT150(Round((getBase150() + FT) * 0.07f)); //=ROUND((D13+D15)*0.07,2)
         setSummary150(getBase150() + FT + VAT150);


    }

    private  void setService(float Service){
        this.Service = Service;
    }
    private  void setService150(float Service150){
        this.Service150 = Service150;
    }
    private void setPower(float Power){
        this.Power = Power;
    }
    private void setBase(float Base){
        this.Base = Base;
    }
    private void setSummary(float Summary){
        this.Summary = Summary;

    }
    private  void setPower150(float Power150){
        this.Power150 = Power150;
    }
    private void setBase150(float Base150){
        this.Base150 = Base150;
    }
    private void  setVAT150(float VAT150){
        this.VAT150 = VAT150;
    }
    private void setSummary150(float Summary150){
        this.Summary150 = Summary150;
    }
    public float getService(){
        return Service;
    }
    public float getPower(){
        return Power;
    }
    public float getBase(){
        return Base;
    }
    public float getSummary(){
        return Summary;
    }
    public float getFT(){
        return FT;
    }
    public float getVat(){
        return VAT;
    }
    public float getBase150(){
        return Base150;
    }
    public float getPower150(){
        return Power150;
    }
    public float getService150(){
        return Service150;
    }
    public float getVAT150(){
        return VAT150;
    }
    public float getSummary150(){
        return Summary150;
    }


    private float Round(float number) {
        return (float)Math.round(number * 100) / 100;
    }
}
