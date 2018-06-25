package corejava.annotation.chart;
public class ChartBeanBeanInfo extends java.beans.SimpleBeanInfo
{
   public java.beans.PropertyDescriptor[] getPropertyDescriptors()
   {
      try
      {
         java.beans.PropertyDescriptor titleDescriptor
            = new java.beans.PropertyDescriptor("title", corejava.annotation.chart.ChartBean.class);
         java.beans.PropertyDescriptor valuesDescriptor
            = new java.beans.PropertyDescriptor("values", corejava.annotation.chart.ChartBean.class);
         valuesDescriptor.setPropertyEditorClass(DoubleArrayEditor.class);
         java.beans.PropertyDescriptor inverseDescriptor
            = new java.beans.PropertyDescriptor("inverse", corejava.annotation.chart.ChartBean.class);
         inverseDescriptor.setPropertyEditorClass(InverseEditor.class);
         java.beans.PropertyDescriptor titlePositionDescriptor
            = new java.beans.PropertyDescriptor("titlePosition", corejava.annotation.chart.ChartBean.class);
         titlePositionDescriptor.setPropertyEditorClass(TitlePositionEditor.class);
         java.beans.PropertyDescriptor graphColorDescriptor
            = new java.beans.PropertyDescriptor("graphColor", corejava.annotation.chart.ChartBean.class);
         return new java.beans.PropertyDescriptor[]
         {
            titleDescriptor,
            valuesDescriptor,
            inverseDescriptor,
            titlePositionDescriptor,
            graphColorDescriptor
         };
      }
      catch (java.beans.IntrospectionException e)
      {
         e.printStackTrace();
         return null;
      }
   }
}
