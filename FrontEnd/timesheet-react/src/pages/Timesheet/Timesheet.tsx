import React, { useState } from 'react';
import { Button, message } from 'antd';
import { history } from 'umi';
import type { ProColumns } from '@ant-design/pro-table';
import { EditableProTable } from '@ant-design/pro-table';
import ProForm, { ProFormSelect, ProFormText, ProFormUploadButton } from '@ant-design/pro-form';

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

type DataSourceType = {
  day?: React.Key;
  date?: string;
  startTime?: string;
  endTime?: string;
  totalHours?: number;
  floating?: boolean;
  vacation?: boolean;
  holiday?: boolean;
};

const data = [
  {
    id: '1',
    userId: '1',
    weekEnding: '2021-07-05',
    totalBillingHour: 32,
    totalCompensatedHour: 40,
    submissionStatus: 'Not Started',
    approvalStatus: 'N/A',
    days: [
      {
        day: 'Monday',
        date: '2021-06-28',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Tuesday',
        date: '2021-06-29',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Wednesday',
        date: '2021-06-30',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Thursday',
        date: '2021-07-01',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Friday',
        date: '2021-07-02',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Saturday',
        date: '2021-07-03',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Sunday',
        date: '2021-07-04',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: true,
        holiday: false,
      },
    ],
  },
  {
    id: '2',
    userId: '2',
    weekEnding: '2021-06-27',
    totalBillingHour: 32,
    totalCompensatedHour: 40,
    submissionStatus: 'Not Started',
    approvalStatus: 'N/A',
    days: [
      {
        day: 'Monday',
        date: '2021-06-21',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Tuesday',
        date: '2021-06-22',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Wednesday',
        date: '2021-06-23',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Thursday',
        date: '2021-06-24',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Friday',
        date: '2021-06-25',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Saturday',
        date: '2021-06-26',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: false,
        holiday: false,
      },
      {
        day: 'Sunday',
        date: '2021-06-27',
        startTime: 'N/A',
        endTime: 'N/A',
        totalHours: 0,
        floating: false,
        vacation: true,
        holiday: false,
      },
    ],
  },
];

const columns: ProColumns<DataSourceType>[] = [
  {
    title: 'Week Day',
    dataIndex: 'day',
    width: '15%',
    editable: (text, record, index) => {
      return index == null;
    },
  },
  {
    title: 'Date',
    dataIndex: 'date',
    width: '15%',
  },
  {
    title: 'Starting Time',
    dataIndex: 'startTime',
    width: '15%',
  },
  {
    title: 'Ending Time',
    dataIndex: 'endTime',
    width: '15%',
  },
  {
    title: 'total Hours',
    dataIndex: 'totalHours',
    width: '15%',
  },
  {
    title: 'Floating',
    dataIndex: 'floating',
    width: '10%',
  },
  {
    title: 'Vacation',
    dataIndex: 'vacation',
    width: '10%',
  },
  {
    title: 'Holiday',
    dataIndex: 'holiday',
    width: '10%',
  },
];

function Timesheet() {

  const [weekEnding, setWeekEnding] = useState(data[0].weekEnding);
  const [dataSource, setDataSource] = useState<DataSourceType[]>(() => data[0].days);

  // @ts-ignore
  const [editableKeys, setEditableRowKeys] = useState<React.Key[]>(() => {
    return dataSource.map((item) => item.day);
  });
  const [weekEndingOptions, setWeekEndingOptions] = useState(() =>
    data.map((item) => item.weekEnding),
  );

  const getDaysByWeekEnding = (val: string) => {
    // @ts-ignore
    return data.find((item) => item.weekEnding === val).days;
  };

  const getKeysByWeekEnding = (val: string) => {
    const days = getDaysByWeekEnding(val);
    return days.map((item) => item.day);
  };

  const weekEndingHandleChange = (val: string) => {
    // routing logic here
    history.push(`/timesheet/${val}`);
    setWeekEnding(val);
    setDataSource(getDaysByWeekEnding(weekEnding));
    setEditableRowKeys(getKeysByWeekEnding(weekEnding));
    // eslint-disable-next-line no-console
    console.log(weekEnding);
    console.log(dataSource);
    console.log(editableKeys);
  };

  return (
    <>
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
            options={weekEndingOptions}
            name="weekEnding"
            label="Week Ending"
            fieldProps={{
              onChange: (val) => weekEndingHandleChange(val),
            }}
            placeholder="Select your week Ending"
          />
          <ProFormText width="md" name="totalBillingHour" disabled label="Total Billing Hours" />
          <ProFormText
            width="md"
            name="totalCompensatedHour"
            disabled
            label="Total Compensated Hours"
          />
        </ProForm.Group>

        <ProForm.Group>
          <EditableProTable<DataSourceType>
            headerTitle="Timesheet Hours Detail"
            columns={columns}
            rowKey="day"
            value={dataSource}
            onChange={setDataSource}
            toolBarRender={() => {
              return [
                <Button
                  type="primary"
                  key="save"
                  onClick={() => {
                    console.log(dataSource);
                  }}
                >
                  Save
                </Button>,
              ];
            }}
            editable={{
              type: 'multiple',
              editableKeys,
              onValuesChange: (record, recordList) => {
                setDataSource(recordList);
              },
              onChange: setEditableRowKeys,
            }}
          />
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
    </>
  );
}

export default Timesheet;
