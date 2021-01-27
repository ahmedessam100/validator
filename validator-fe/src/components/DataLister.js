import React, { Component } from 'react';
import Rows from './Rows';

class DataLister extends Component {

    constructor(props) {
        super(props);
        this.state = {
            dataReady: false,
            data: [],
            originalData: [],
            filterKey: '',
            selection: '',
            error: null
        };
    }

    fetchData = () => {
        fetch('http://localhost:8080/getData')
            .then((res) => res.json())
            .then((res) => this.setState({
                data: res.reduce((customers, currCustomer) => {

                    let customer = {
                        name: currCustomer.name,
                        phoneNumber: currCustomer.phoneNumber,
                        code: currCustomer.code,
                        country: currCustomer.country,
                        validation: currCustomer.state
                    };

                    customers.push(customer);

                    return customers;
                }, []),

                dataReady: true
            }))
            .then(() => {
                this.setState({ originalData: [...this.state.data] } )
            })
            .catch((e) => this.setState({ error: e, dataReady: false }));
    };

    fetchDataByCountry = () => {

        fetch('http://localhost:8080/getDataByCountry/' + this.state.filterKey)
            .then((res) => res.json())
            .then((res) => this.setState({
                data: res.reduce((customers, currCustomer) => {

                    let customer = {
                        name: currCustomer.name,
                        phoneNumber: currCustomer.phoneNumber,
                        code: currCustomer.code,
                        country: currCustomer.country,
                        validation: currCustomer.state
                    };

                    customers.push(customer);

                    return customers;
                }, []),

                dataReady: true
            }))
            .catch((e) => this.setState({ error: e, dataReady: false }));
    };

    fetchDataByState = () => {

        fetch('http://localhost:8080/getDataByState/' + this.state.filterKey)
            .then((res) => res.json())
            .then((res) => this.setState({
                data: res.reduce((customers, currCustomer) => {

                    let customer = {
                        name: currCustomer.name,
                        phoneNumber: currCustomer.phoneNumber,
                        code: currCustomer.code,
                        country: currCustomer.country,
                        validation: currCustomer.state
                    };

                    customers.push(customer);

                    return customers;
                }, []),

                dataReady: true
            }))
            .catch((e) => this.setState({ error: e, dataReady: false }));
    };


    filterData = (e) => {
        if(this.state.selection === 'country')
        {
            this.fetchDataByCountry();
        }
        else if(this.state.selection === 'validation')
        {
            this.fetchDataByState();
        }
    };

    handleChange = (e) => {

        if (e.target.id === 'filterSelection')
        {
            this.setState({ selection: e.target.value });
        }
        else if (e.target.id === 'searchKey')
        {
            if (e.target.value === '')
                this.reset();

            this.setState({ filterKey : e.target.value });
        }
    };

    reset = () => {
        /* reset the data */
        this.setState({
            data: [...this.state.originalData]
        })
    };

    componentDidMount() {
        this.fetchData();
    }

    render() {

        /* If the data is not loaded */
        if (!this.state.dataReady || this.state.error)
        {
            return this.state.error ? <p>{this.state.error.message}</p> : <div />
        }

        /* Filling the table with rows */
        const rows: JSX.Element[] = [];
        rows.push(<tr>
                        <th style={{textAlign: "center"}}>Name</th>
                        <th style={{textAlign: "center"}}>Country</th>
                        <th style={{textAlign: "center"}}>Code</th>
                        <th style={{textAlign: "center"}}>Phone</th>
                        <th style={{textAlign: "center"}}>State</th>
                  </tr>);

        let customer;

        for (let i = 0; i < this.state.data.length; i++)
        {
            customer = this.state.data[i];

            rows.push(<Rows customerName={customer.name} phone={customer.phoneNumber} country={customer.country} code={'+' + customer.code} state={customer.validation}/>)
        }

        return (
                <div style={{overflow:"auto"}} >
                    <label style={{paddingLeft: 900, fontSize: 30}}>Filter</label>

                    <select id={"filterSelection"} onChange={ this.handleChange }>
                        <option value="" selected disabled hidden>Select</option>
                        <option value={"country"}>Country</option>
                        <option value={"validation"}>State</option>
                    </select>

                    <input type="text" id="searchKey" name="key" onChange={ this.handleChange }/>
                    <input type={"submit"} id={"filter"} value={"Filter"} onClick={ this.filterData }/>
                    <input type={"submit"} id={"reset"}  value={"Reset"} onClick={ this.reset }/>

                    <table>
                        {rows}
                    </table>
                </div>
        );
    }
}

export default DataLister;
