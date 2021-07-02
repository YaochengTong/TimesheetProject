import { Settings as LayoutSettings } from '@ant-design/pro-layout';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: 'Super3 Timesheet Project',
  pwa: false,
  logo: 'https://markdown-bucket.s3.us-east-2.amazonaws.com/uPic/Icon-notepad_2021_07_02_04_52_28.svg',
  iconfontUrl: '',
};

export default Settings;
