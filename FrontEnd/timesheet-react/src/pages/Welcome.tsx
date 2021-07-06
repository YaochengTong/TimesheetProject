import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Alert, Calendar, Card } from 'antd';
import { useIntl } from 'umi';

export default (): React.ReactNode => {
  function onPanelChange(value: { format: (arg0: string) => any }, mode: any) {
    // eslint-disable-next-line no-console
    console.log(value.format('YYYY-MM-DD'), mode);
  }

  const intl = useIntl();
  return (
    <PageContainer>
      <Card>
        <Alert
          message={intl.formatMessage({
            id: 'pages.welcome.alertMessage',
            defaultMessage: 'Welcome to Super3 Timesheet Project',
          })}
          type="success"
          showIcon
          banner
          style={{
            margin: -12,
            marginBottom: 24,
          }}
        />
      </Card>
      <Calendar onPanelChange={onPanelChange} />
    </PageContainer>
  );
};
