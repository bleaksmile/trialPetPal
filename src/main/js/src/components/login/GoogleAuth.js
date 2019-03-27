import React, { useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';

const GoogleAuth = ({ location, history }) => {
  useEffect(() => {
    const queries = qs.parse(location.search);
    console.log(queries.googleauth);
    localStorage.setItem('accesstoken', queries.googleauth);
    if (localStorage.getItem('accesstoken')) {
      history.push('/home/find');
    } else {
      history.push('/');
    }
  }, []);
  return <div>redirecting...</div>;
};

export default withRouter(GoogleAuth);
