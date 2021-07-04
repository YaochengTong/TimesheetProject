import {Component} from 'react';
import {Button, Form, Input} from 'antd';

class Profile extends Component {
  render() {
    return (
      <Form labelCol={{span: 9}} wrapperCol={{span: 5, offset: 9}} layout="horizontal">
        <Form.Item>
          <h1>Contact</h1>
          <Input placeholder="Phone Number" name = "phone"/>
        </Form.Item>

        <Form.Item>
          <Input placeholder="Email" name = "email"/>
        </Form.Item>
        <Form.Item>
          <Input placeholder="Address" name = "address"/>
        </Form.Item>

        <Form.Item>
          <h2>Emergency Contact 1</h2>
        </Form.Item>
        <Form.Item>
          <Input placeholder="Name" name="ec1Name"/>
        </Form.Item>
        <Form.Item>
          <Input placeholder="Phone Number" name="ec1Phone"/>
        </Form.Item>

        <Form.Item>
          <h2>Emergency Contact 2</h2>
        </Form.Item>
        <Form.Item>
          <Input placeholder="Name" name="ec2Name"/>
        </Form.Item>
        <Form.Item>
          <Input placeholder="Phone Number" name="ec2Phone"/>
        </Form.Item>

        <Form.Item style={{textAlign: 'center'}}>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Save
          </Button>
        </Form.Item>
      </Form>
    );
  }
}

export default Profile;
