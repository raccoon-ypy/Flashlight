package com.yangpeiyong.flashlight;

import android.hardware.Camera;
import android.util.Log;

import java.util.List;

public class PowerLED {
    public static final String TAG = "PowerLED";
	boolean m_isOn;
	Camera m_Camera;
    boolean supportTorch = false;
	public boolean getIsOn() { return m_isOn; }
	
	public PowerLED()
	{
		m_isOn = false;

		m_Camera = Camera.open();

        Camera.Parameters mParameters = m_Camera.getParameters();
        List<String> supportMode = mParameters.getSupportedFlashModes();

        for(int i=0;i<supportMode.size();i++){
            Log.d(TAG,supportMode.get(i));
            if(supportMode.get(i).equals(Camera.Parameters.FLASH_MODE_TORCH))
            {
                supportTorch = true;
                break;
            }
        }

        if(!supportTorch){
            m_Camera.release();
            m_Camera=null;
        }

    }
	
	public boolean turnOn()
	{
        if(!supportTorch){
            return false;
        }

		if(!m_isOn)
		{
			m_isOn = true;
			try
			{
				Camera.Parameters mParameters;
                m_Camera.startPreview();
				mParameters = m_Camera.getParameters();
                List<String> supportMode = mParameters.getSupportedFlashModes();

				mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

				m_Camera.setParameters(mParameters);
			}catch(Exception ex){}
		}
        return true;
	}
	
	public boolean turnOff()
	{
        if(!supportTorch){
            return false;
        }
		if(m_isOn)
		{
			m_isOn = false;
			try
			{
				Camera.Parameters mParameters;
				mParameters = m_Camera.getParameters();
				mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				m_Camera.setParameters(mParameters);
                m_Camera.stopPreview();
			}catch(Exception ex){}
		}
        return true;
	}
	
	public void Destroy()
	{
        if(m_Camera!=null) {
            turnOff();
            m_Camera.release();
        }
	}
	
}
