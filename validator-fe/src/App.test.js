import App from './App';
import React from "react";
import { shallow, mount } from "enzyme";
import DataLister from "./components/DataLister";
import Rows from "./components/Rows";
import {render, fireEvent, cleanup} from '@testing-library/react';
import toJson from "enzyme-to-json";

afterEach(cleanup);

const customer = {
  name: 'test',
  country: 'Morocco',
  code: '212',
  phone: '6007989253',
  phoneNumber: '(212) 6007989253',
  validation: 'not valid',
  regex: '\\(212\\)\\ ?[5-9]\\d{8}$'
};

describe('Testing the components to render without crashing', () => {
  it('renders without crashing', () => {
    shallow(<App/>);
  });

  it('renders data component without crashing', () => {
    shallow(<DataLister />);
  });

  it('renders rows component without crashing', () => {
    shallow(<Rows />);
  });
});

it("DataLister component renders correctly with no error message", () => {
  const wrapper = mount(<DataLister />);
  expect(wrapper.state("error")).toEqual(null);
});

describe('Testing data transfer between DataLister and Rows components', () => {

  it('accepts customer props', () => {
    const wrapper = mount(<Rows customerName={customer.name} phone={customer.phone} country={customer.country} code={customer.code} state={customer.state} />);
    expect(wrapper.props().customerName).toEqual(customer.name);
    expect(wrapper.props().phone).toEqual(customer.phone);
    expect(wrapper.props().country).toEqual(customer.country);
    expect(wrapper.props().code).toEqual(customer.code);
    expect(wrapper.props().state).toEqual(customer.state);
  });

  it('contains customer data', () => {
    const wrapper = mount(<Rows customerName={customer.name} phone={customer.phone} country={customer.country} code={customer.code} state={customer.state}/>);
    expect(wrapper.find({id: "name"}).getElement().props.children).toEqual(customer.name);
    expect(wrapper.find({id: "phone"}).getElement().props.children).toEqual(customer.phone);
    expect(wrapper.find({id: "country"}).getElement().props.children).toEqual(customer.country);
    expect(wrapper.find({id: "code"}).getElement().props.children).toEqual(customer.code);
    expect(wrapper.find({id: "state"}).getElement().props.children).toEqual(customer.state);
  });
});

it("renders correctly", () => {
  const tree = mount(<App />);
  expect(toJson(tree)).toMatchSnapshot();
});

it('phone number validation functioning correctly', () => {
  const wrapper = shallow(<DataLister />);
  let value = wrapper.instance().isValid(customer.phoneNumber, customer.code);
  expect(value).toEqual(customer.validation);
});
