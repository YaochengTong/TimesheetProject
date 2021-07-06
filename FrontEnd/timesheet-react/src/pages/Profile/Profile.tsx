import ProForm, { ProFormText } from '@ant-design/pro-form';
import { message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

const Profile = () => {
  return (
    <PageContainer title="Contact Info">
      <ProForm<{
        phone: string;
        email?: string;
        address?: string;
        ec1Name?: string;
        ec1Phone?: string;
        ec2Name?: string;
        ec2Phone?: string;
      }>
        onFinish={async (values) => {
          await waitTime(2000);
          console.log(values);
          message.success('提交成功');
        }}
        params={{}}
        // @ts-ignore
        request={async () => {
          await waitTime(100);
          return {};
        }}
      >
        <ProForm.Group>
          <ProFormText
            width="md"
            name="phone"
            label="Your phone"
            tooltip="maxinum 9 digits"
            placeholder="enter your phone number"
          />
          <ProFormText
            width="md"
            name="email"
            label="Your email"
            tooltip="Enter with @"
            placeholder="enter your email"
          />
          <ProFormText
            width="md"
            name="address"
            label="Your address"
            tooltip="Please match with USPS"
            placeholder="enter your address"
          />
        </ProForm.Group>

        <ProForm.Group>
          <ProFormText
            width={'md'}
            name={'ec1Name'}
            label="Contact 1 Name"
            placeholder={'Enter Contact 1 Name'}
          />
          <ProFormText
            width={'md'}
            name="ec1Phone"
            label="Contact 1 Phone"
            placeholder={'Enter Contact 1 Phone'}
          />
        </ProForm.Group>

        <ProForm.Group>
          <ProFormText
            width={'md'}
            name={'ec2Name'}
            label="Contact 2 Name"
            placeholder={'Enter Contact 2 Name'}
          />
          <ProFormText
            width={'md'}
            name="ec2Phone"
            label="Contact 2 Phone"
            placeholder={'Enter Contact 2 Phone'}
          />
        </ProForm.Group>
      </ProForm>
    </PageContainer>
  );
};

export default Profile;
