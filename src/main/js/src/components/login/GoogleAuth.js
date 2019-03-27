import React, { useEffect } from 'react';

const GoogleAuth = ({ location }) => {
  useEffect(() => {
    console.log(location);
  }, []);
  return <div>redirecting...</div>;
};

export default GoogleAuth;
