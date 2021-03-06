import React, { useEffect, useState } from 'react';
import { Button, message } from 'antd';
import { history } from 'umi';
import type { ProColumns } from '@ant-design/pro-table';
import { EditableProTable } from '@ant-design/pro-table';
import type { ProFormColumnsType } from '@ant-design/pro-form';
import ProForm, {
  BetaSchemaForm,
  ProFormSelect,
  ProFormText,
  ProFormUploadButton,
} from '@ant-design/pro-form';
import axios from 'axios';
import { PageContainer } from '@ant-design/pro-layout';

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

// type DataSourceType = {
//   day?: React.Key;
//   date?: string;
//   startTime?: string;
//   endTime?: string;
//   totalHours?: number;
//   floating?: boolean;
//   vacation?: boolean;
//   holiday?: boolean;
// };

const data1 = [
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

const disabledDayColumns: ProColumns[] = [
  {
    title: 'Week Day',
    dataIndex: 'day',
    valueType: 'select',
    width: '15%',
    editable: false,
    valueEnum: {
      Monday: {
        text: 'Monday',
      },
      Tuesday: {
        text: 'Tuesday',
      },
      Wednesday: {
        text: 'Wednesday',
      },
      Thursday: {
        text: 'Thursday',
      },
      Friday: {
        text: 'Friday',
      },
      Saturday: {
        text: 'Saturday',
      },
      Sunday: {
        text: 'Sunday',
      },
    },
  },
  {
    title: 'Date',
    dataIndex: 'date',
    width: '15%',
    editable: false,
  },
  {
    title: 'Starting Time',
    dataIndex: 'startTime',
    width: '15%',
    editable: false,
    // editable: (text, record, index) => {
    //   if (record.approvalStatus === 'Approved') {
    //     return index !== null;
    //   }
    //   return index == null;
    // },
  },
  {
    title: 'Ending Time',
    dataIndex: 'endTime',
    width: '15%',
    editable: false,
  },
  {
    title: 'total Hours',
    dataIndex: 'totalHours',
    width: '15%',
    editable: false,
  },
  {
    title: 'Floating',
    dataIndex: 'floating',
    width: '10%',
    editable: false,
    valueType: 'select',
    valueEnum: {
      true: {
        text: 'true',
      },
      false: {
        text: 'false',
      },
    },
  },
  {
    title: 'Vacation',
    dataIndex: 'vacation',
    width: '10%',
    editable: false,
    valueType: 'select',
    valueEnum: {
      true: {
        text: 'true',
      },
      false: {
        text: 'false',
      },
    },
  },
  {
    title: 'Holiday',
    dataIndex: 'holiday',
    width: '10%',
    editable: false,
    valueType: 'select',
    valueEnum: {
      true: {
        text: 'true',
      },
      false: {
        text: 'false',
      },
    },
  },
];

const dayColumns: ProColumns[] = [
  {
    title: 'Week Day',
    dataIndex: 'day',
    valueType: 'select',
    width: '15%',
    editable: (text, record, index) => {
      return index !== null;
    },
    valueEnum: {
      Monday: {
        text: 'Monday',
      },
      Tuesday: {
        text: 'Tuesday',
      },
      Wednesday: {
        text: 'Wednesday',
      },
      Thursday: {
        text: 'Thursday',
      },
      Friday: {
        text: 'Friday',
      },
      Saturday: {
        text: 'Saturday',
      },
      Sunday: {
        text: 'Sunday',
      },
    },
  },
  {
    title: 'Date',
    dataIndex: 'date',
    width: '15%',
    editable: (text, record, index) => {
      return index == null;
    },
  },
  {
    title: 'Starting Time',
    dataIndex: 'startTime',
    width: '15%',
    // editable: (text, record, index) => {
    //   if (record.approvalStatus === 'Approved') {
    //     return index !== null;
    //   }
    //   return index == null;
    // },
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
    // valueType: 'select',
    // valueEnum: {
    //   true: {
    //     text: 'true',
    //   },
    //   false: {
    //     text: 'false',
    //   },
    // },
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
// Add timesheet configuration
type DataItem = {
  userId: string;
  submissionStatus: string;
  comment: string;
  weekEnding: string;
  totalBillingHour: string;
  totalCompensatedHour: string;
  approvalStatus: string;
  days: [];
};

const schemaColumns: ProFormColumnsType<DataItem>[] = [
  {
    title: 'weekEnding',
    dataIndex: 'weekEnding',
    initialValue: '2021-07-06',
    formItemProps: {
      rules: [
        {
          required: true,
          message: 'Week Ending is necessary',
        },
      ],
    },
    width: 'm',
  },
  {
    title: 'totalBillingHour',
    dataIndex: 'totalBillingHour',
    initialValue: '40',
    width: 'm',
  },
  {
    title: 'totalCompensatedHour',
    dataIndex: 'totalCompensatedHour',
    initialValue: '40',
    width: 'm',
  },
  {
    title: 'Days',
    valueType: 'formList',
    dataIndex: 'days',
    columns: [
      {
        valueType: 'group',
        columns: [
          {
            title: 'Week Day',
            dataIndex: 'day',
            valueType: 'select',
            initialValue: 'Monday',
            width: 'xs',
            valueEnum: {
              Monday: {
                text: 'Monday',
              },
              Tuesday: {
                text: 'Tuesday',
              },
              Wednesday: {
                text: 'Wednesday',
              },
              Thursday: {
                text: 'Thursday',
              },
              Friday: {
                text: 'Friday',
              },
              Saturday: {
                text: 'Saturday',
              },
              Sunday: {
                text: 'Sunday',
              },
            },
          },
          {
            title: 'Date',
            dataIndex: 'date',
            initialValue: '2021-07-06',
            width: 's',
          },
          {
            title: 'Starting Time',
            dataIndex: 'startTime',
            initialValue: '9',
            width: 'xs',
          },
          {
            title: 'Ending Time',
            dataIndex: 'endTime',
            initialValue: '5',
            width: 'xs',
          },
          {
            title: 'total Hours',
            dataIndex: 'totalHours',
            initialValue: '8',
            width: 'xs',
          },
          {
            title: 'Floating',
            dataIndex: 'floating',
            valueType: 'select',
            width: 'xs',
            initialValue: 'false',
            valueEnum: {
              true: {
                text: 'true',
              },
              false: {
                text: 'false',
              },
            },
          },
          {
            title: 'Vacation',
            dataIndex: 'vacation',
            valueType: 'select',
            width: 'xs',
            initialValue: 'false',
            valueEnum: {
              true: {
                text: 'true',
              },
              false: {
                text: 'false',
              },
            },
          },
          {
            title: 'Holiday',
            dataIndex: 'holiday',
            valueType: 'select',
            initialValue: 'false',
            width: 'xs',
            valueEnum: {
              true: {
                text: 'true',
              },
              false: {
                text: 'false',
              },
            },
          },
        ],
      },
    ],
  },
  {
    title: 'approvalStatus',
    valueType: 'select',
    dataIndex: 'approvalStatus',
    width: 'm',
    valueEnum: {
      Approved: {
        text: 'approvalTimesheet',
      },
      'Not approved': {
        text: 'unapprovedTimesheet',
      },
    },
  },
];

function Timesheet(this: any) {
  const [data, setData] = useState(data1);
  const [weekEnding, setWeekEnding] = useState(data[0].weekEnding);
  const [daysData, setDataSource] = useState(() => data[0].days);
  const [editableColumns, setEditableColumns] = useState(dayColumns);
  // @ts-ignore
  const [editableKeys, setEditableRowKeys] = useState<React.Key[]>(() => {
    return daysData.map((item) => item.day);
  });
  const [weekEndingOptions, setWeekEndingOptions] = useState(() =>
    data.map((item) => item.weekEnding),
  );

  const [curBillingHour, setCurBillingHour] = useState(data[0].totalBillingHour);

  const getDaysByWeekEnding = (val: string) => {
    // @ts-ignore
    return data.find((item) => item.weekEnding === val).days;
  };

  const getApprovalStatusByWeekEnding = (val: string) => {
    // @ts-ignore
    return data.find((item) => item.weekEnding === val).approvalStatus;
  };

  const getKeysByWeekEnding = (val: string) => {
    const days = getDaysByWeekEnding(val);
    return days.map((item) => item.day);
  };

  const weekEndingHandleChange = (val: string) => {
    // routing logic here
    console.log(`weekEndingHandleChange ${val}`);
    history.push(`/timesheet/${val}`);
    setWeekEnding(val);
    setDataSource(getDaysByWeekEnding(val));
    setEditableRowKeys(getKeysByWeekEnding(val));
    if (getApprovalStatusByWeekEnding(val) === 'Approved') {
      setEditableColumns(disabledDayColumns);
    } else {
      setEditableColumns(dayColumns);
    }
  };

  const saveChangeHandler = () => {
    const curWeek = weekEnding;

    const week = data.find((item) => item.weekEnding === curWeek);
    if (week === undefined) return;

    let totalHours = 0;
    // Calculate total hours
    daysData.forEach((item) => {
      const tempStartTimeArr = item.startTime.split(':');
      const tempEndTimeArr = item.endTime.split(':');
      if (tempStartTimeArr.length === 2 && tempEndTimeArr.length === 2) {
        const tempStartHour = parseInt(tempStartTimeArr[0], 10);
        const tempStartMinute = parseInt(tempStartTimeArr[1], 10);
        const tempEndHour = parseInt(tempEndTimeArr[0], 10);
        const tempEndMinute = parseInt(tempEndTimeArr[1], 10);

        const startTotal = tempStartHour * 60 + tempStartMinute;
        const endTotal = tempEndHour * 60 + tempEndMinute;
        const difference = endTotal - startTotal;

        const result = (difference / 60).toFixed(1);
        item.totalHours = parseFloat(result);
      }
      totalHours += item.totalHours;
    });
    week.days = daysData;
    week.totalBillingHour = totalHours;

    console.log(daysData);
    console.log(week);

    axios
      .put('http://localhost:8081/timeSheet/update', week, {
        headers: { 'Access-Control-Allow-Origin': '*' },
      })
      .then((item) => console.log(item))
      .catch((err) => console.log(err));

    message.success('You have successfully submitted your timesheet!');
    window.location.reload();
  };

  const fetchTimeSheetData = async () => {
    const userId = 1;

    const allSummaryURL = `http://localhost:8081/timeSheet/summary?userId=${userId}`;

    await axios.get(allSummaryURL).then((items) => {
      setData(items.data);
      console.log(items.data);
    });
  };

  useEffect(() => {
    fetchTimeSheetData();
    console.log(data);
  }, []);

  useEffect(() => {
    setWeekEndingOptions(data.map((item) => item.weekEnding));
    console.log(weekEndingOptions);
  }, [data]);

  useEffect(() => {
    const curWeek = weekEnding;

    const week = data.find((item) => item.weekEnding === curWeek);

    if (week !== undefined) setCurBillingHour(week.totalBillingHour);

    console.log(curBillingHour);
  }, [curBillingHour, data, weekEnding]);

  return (
    <PageContainer title="Timesheet Info">
      <div style={{ float: 'right' }}>
        <BetaSchemaForm<DataItem>
          trigger={<Button type="primary">Add new Timesheet Manually</Button>}
          layoutType="DrawerForm"
          onFinish={async (values) => {
            // Manually add some parameter
            // eslint-disable-next-line no-param-reassign
            values.userId = '1';
            // eslint-disable-next-line no-param-reassign
            values.submissionStatus = 'Incomplete';
            // eslint-disable-next-line no-param-reassign
            values.comment = 'New Comment';
            // eslint-disable-next-line no-param-reassign
            values.approvalStatus = 'Not approved';
            // eslint-disable-next-line no-console
            console.log(values);

            axios
              .post('http://localhost:8081/timeSheet/add', values, {
                headers: {
                  'Access-Control-Allow-Origin': '*',
                  'Content-Type': 'application/json',
                },
              })
              .then((item) => console.log(item))
              .catch((err) => console.log(err));
            message.success('You have successfully submitted your timesheet!');
            window.location.reload();
          }}
          columns={schemaColumns}
          width={1300}
        />
      </div>

      <ProForm<{
        name: string;
        company?: string;
        useMode?: string;
      }>
        initialValues={{
          date: Date.now(),
          totalCompensatedHour: 40,
        }}
        onFinish={async (values) => {
          console.log(values);
          await waitTime(1000);
          // @ts-ignore
          // eslint-disable-next-line no-param-reassign
          values.userId = '1';
          // eslint-disable-next-line no-console
          axios
            .put('http://localhost:8081/timeSheet/updateApprovalStatus', values, {
              headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
              },
            })
            .then((item) => console.log(item))
            .catch((err) => console.log(err));
          message.success('You have successfully submitted your timesheet!');
          window.location.reload();
        }}
        params={{}}
        request={async () => {
          await waitTime(100);
          return {
            name: 'Super3 Group',
            useMode: 'chapter',
          };
        }}
      >
        <ProForm.Group key={curBillingHour}>
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

          <label>Total Billing Hour: </label>
          <input disabled value={curBillingHour} />

          <ProFormText
            width="md"
            name="totalCompensatedHour"
            disabled
            label="Total Compensated Hours"
          />
        </ProForm.Group>

        <ProForm.Group key={weekEnding}>
          <EditableProTable
            headerTitle="Timesheet Hours Detail"
            columns={editableColumns}
            rowKey="day"
            value={daysData}
            onChange={setDataSource}
            // recordCreatorProps={false} ?????????????????????
            toolBarRender={() => {
              return [
                <Button type="primary" key="save" onClick={() => saveChangeHandler()}>
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
            // initialValue={}
            options={[
              {
                value: 'Not approved',
                label: 'Unapproved Timesheet',
              },
              {
                value: 'Approved',
                label: 'Approved Timesheet',
              },
            ]}
            name="approvalStatus"
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
    </PageContainer>
  );
}

export default Timesheet;
