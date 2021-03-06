﻿using System;
using System.Windows;
using System.Windows.Data;

namespace CustomListBox
{
    [ValueConversion(typeof(String), typeof(Visibility))]
    public class ProgressConverter : IValueConverter
    {
        #region IValueConverter メンバ

        public object Convert(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            Visibility result = Visibility.Visible;
            try
            {
                int progress = int.Parse(value.ToString());
                if (progress >= 100)
                {
                    result = Visibility.Hidden;
                }
            }
            catch (Exception)
            {
            }
            return result;
        }

        public object ConvertBack(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
