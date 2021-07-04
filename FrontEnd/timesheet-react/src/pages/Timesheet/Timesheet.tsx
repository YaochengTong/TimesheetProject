import ProForm, { ProFormSelect, ProFormText, ProFormUploadButton } from '@ant-design/pro-form';
import { Component } from 'react';
import { message, Space, Table } from 'antd';
import DayOffStatus from '@/pages/Timesheet/components/DayOffStatus';

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

const columns = [
  {
    title: 'Day',
    dataIndex: 'day',
    key: 'day',
  },
  {
    title: 'Date',
    dataIndex: 'date',
    key: 'date',
  },
  {
    title: 'Starting Time',
    dataIndex: 'startTime',
    key: 'startTime',
  },
  {
    title: 'Ending Time',
    dataIndex: 'endingTime',
    key: 'endingTime',
  },
  {
    title: 'Total Hours',
    dataIndex: 'totalHours',
    key: 'totalHours',
  },
  {
    title: 'Floating Day',
    dataIndex: 'floatingDay',
    key: 'floatingDay',
    render: (text: any, record: any) => (
      <Space size="middle">
        <DayOffStatus status={record.floatingDay} />
      </Space>
    ),
  },
  {
    title: 'Holiday',
    dataIndex: 'holiday',
    key: 'holiday',
    render: (text: any, record: any) => (
      <Space size="middle">
        <DayOffStatus status={record.holiday} />
      </Space>
    ),
  },
  {
    title: 'Vacation',
    dataIndex: 'vacation',
    key: 'vacation',
    render: (text: any, record: any) => (
      <Space size="middle">
        <DayOffStatus status={record.vacation} />
      </Space>
    ),
  },
];

const data = [
  {
    day: 0,
    date: '1998-06-08',
    startTime: 'N/A',
    endingTime: 'N/A',
    totalHours: 0,
    floatingDay: false,
    holiday: true,
    vacation: false,
  },
  {
    day: 1,
    date: '1998-06-09',
    startTime: 8,
    endingTime: 12,
    totalHours: 0,
    floatingDay: true,
    holiday: false,
    vacation: false,
  },
];

class Timesheet extends Component {
  constructor(props: any) {
    super(props);
    this.state = {
      rows: [],
      totalBillingHour: 40,
      totalCompensatedHour: 29,
    };
  }

  private dateOptions = [
    {
      value: '2021-07-04',
      label: '2021-07-04',
    },
    {
      value: '2021-07-03',
      label: '2021-07-03',
    },
    {
      value: '2021-07-02',
      label: '2021-07-02',
    },
  ];

  render() {
    return (
      <ProForm<{
        name: string;
        company?: string;
        useMode?: string;
      }>
        initialValues={{
          date: Date.now(),
          totalBillingHour: 40,
          totalCompensatedHour: 29,
        }}
        onFinish={async (values) => {
          await waitTime(2000);
          // eslint-disable-next-line no-console
          console.log(values);
          message.success('You have successfully submitted your timesheet!');
        }}
        params={{}}
        request={async () => {
          await waitTime(100);
          return {
            name: '蚂蚁设计有限公司',
            useMode: 'chapter',
          };
        }}
      >
        <ProForm.Group>
          <ProFormSelect
            width="md"
            options={this.dateOptions}
            name="weekEnding"
            label="Week Ending"
          />
          <ProFormText width="sm" name="totalBillingHour" disabled label="Total Billing Hours" />
          <ProFormText
            width="sm"
            name="totalCompensatedHour"
            disabled
            label="Total Compensated Hours"
          />
        </ProForm.Group>

        <ProForm.Group>
          <Table dataSource={data} columns={columns} pagination={false} />
        </ProForm.Group>

        <ProForm.Group> &nbsp; </ProForm.Group>
        <ProForm.Group>
          <ProFormSelect
            width="md"
            options={[
              {
                value: 'unapprovedTimesheet',
                label: 'Unapproved Timesheet',
              },
              {
                value: 'approvedTimesheet',
                label: 'Approved Timesheet',
              },
            ]}
            name="timesheetStatus"
            label="Select your timesheet status"
          />
          <ProFormUploadButton
            name="upload"
            label="Upload your timesheet"
            max={2}
            fieldProps={{
              name: 'file',
            }}
            action="/upload.do"
          />
        </ProForm.Group>
      </ProForm>
    );
  }
}

export default Timesheet;
